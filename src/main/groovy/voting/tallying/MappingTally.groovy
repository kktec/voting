package voting.tallying

import groovy.transform.Immutable

@Immutable
class MappingTally implements Tally, Map  {

    @Delegate
    Map tally

}
