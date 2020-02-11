package org.henu.alignment.controller

import org.henu.alignment.model.AlignmentEntry
import org.henu.alignment.model.AlignmentRequest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface AlignApi {
    @RequestMapping(value = "/align", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AlignmentEntry>> align(@RequestBody AlignmentRequest request);
}