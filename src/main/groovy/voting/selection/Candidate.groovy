package voting.selection

import groovy.transform.Immutable


@Immutable
class Candidate {

    /** a unique id */
    String id

    @Override
    String toString() { id }
}
