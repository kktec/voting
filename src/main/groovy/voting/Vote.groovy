package voting

import groovy.transform.Immutable

@Immutable
class Vote {

    Voter voter

    Map vote
}
