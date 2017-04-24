package voting.selection

import groovy.transform.Immutable


@Immutable
class Candidate {

    String id

    @Override
    String toString() { id }
}
