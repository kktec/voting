package voting

import groovy.transform.Immutable

@Immutable
class Voter {

    /** a unique id */
    String id

    @Override
    String toString() { id.toString() }
}
