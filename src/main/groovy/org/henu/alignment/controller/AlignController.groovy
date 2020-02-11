package org.henu.alignment.controller

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import groovy.transform.TupleConstructor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.BufferedSink
import org.henu.alignment.model.AlignmentEntry
import org.henu.alignment.model.AlignmentRequest
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody

import java.util.concurrent.TimeUnit

@Controller
class AlignController implements AlignApi {
    @Autowired
    private Environment env
    @Override
    ResponseEntity<List<AlignmentEntry>> align(@RequestBody AlignmentRequest request) {
        return new ResponseEntity<List<AlignmentEntry>>( new Gson().fromJson(Objects.requireNonNull(new OkHttpClient.Builder()
                .readTimeout(16, TimeUnit.MINUTES)
                .writeTimeout(16, TimeUnit.MINUTES).build().newCall(new Request.Builder()
                .url(env.getProperty("master.url")+env.getProperty("api.path"))
                .post(new okhttp3.RequestBody() {
                    @Nullable
                    @Override
                     MediaType contentType() {
                        return MediaType.parse("application/json")
                    }

                    @Override
                     void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
                        bufferedSink.write(new Gson().toJson(request).getBytes())
                        bufferedSink.flush();
                        bufferedSink.close();
                    }
                }).build()).execute().body()).string(), new TypeToken<ArrayList<AlignmentEntry>>() {
        }.getType()),HttpStatus.OK)
    }
}
