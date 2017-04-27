package voting

import spock.lang.Specification

class VotingSpec extends Specification {

    VotingItem itemA = new VotingItem(id: 'A', tallier: { [results: 1] } )

    VotingItem itemB = new VotingItem(id: 'B', tallier: { [results: 'abc'] } )

    VotingItem itemC = new VotingItem(id: 'C', tallier: { [results: [x: 3, y: 4]] } )

    Voting voting = new Voting(items: [itemA, itemB, itemC])

    void 'can tally and aggregate the flattened results for multiple VotingItems'() {
        when:
        Map allResults = voting.tally()

        then:
        allResults == [results: [A: 1, B: 'abc', C: [x: 3, y: 4]]]
    }
}

