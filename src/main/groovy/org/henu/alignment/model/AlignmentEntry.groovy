package org.henu.alignment.model

import groovy.transform.TupleConstructor

@TupleConstructor
class AlignmentEntry {
     Double percentageOfIdentity
     String geneID
     List<AlignedSequence> alignedSequenceList
    @TupleConstructor
    static class AlignedSequence{
        String sequence
        Long starts
        Long ends
        Double coverage
        Long numGaps
    }
}
