package voting

import groovy.transform.Immutable

@Immutable
class Voter {

    String id

    @Override
    String toString() { id }
}
