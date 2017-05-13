package voting.tallying

import groovy.transform.Immutable

@Immutable
class NumberTally extends Number implements Tally {

    @Delegate
    Double tally
}
