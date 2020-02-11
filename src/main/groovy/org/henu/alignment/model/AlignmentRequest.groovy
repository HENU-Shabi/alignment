package org.henu.alignment.model

import groovy.transform.TupleConstructor

@TupleConstructor
class AlignmentRequest {
    String sequence
    Short openPenalty = 5
    Short extensionPenalty = 2
    Double lowestPercent = 0.8
}
