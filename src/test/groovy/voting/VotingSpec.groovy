package voting

import spock.lang.Specification
import voting.tallying.MappingTally
import voting.tallying.NumberTally
import voting.tallying.Tally

class VotingSpec extends Specification {

    VotingItem itemA = new VotingItem(id: 'A', tallier: { new NumberTally( 1) } )

    VotingItem itemB = new VotingItem(id: 'B', tallier: { new MappingTally(x: 3, y: 4) } )

    Voting voting = new Voting(items: [itemA, itemB])

    void 'can tally and aggregate the flattened results for multiple VotingItems'() {
        when:
        Tally allResults = voting.tally()

        then:
        allResults == new MappingTally(A: 1, B: [x: 3, y: 4])
    }
}

