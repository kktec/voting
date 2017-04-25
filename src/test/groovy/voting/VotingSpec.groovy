package voting

import spock.lang.Specification

class VotingSpec extends Specification {

    VotingItem itemA = new VotingItem(id: 'A', tallier: { [results: 1] })

    VotingItem itemB = new VotingItem(id: 'B', tallier: { [results: 2] })

    VotingItem itemC = new VotingItem(id: 'C', tallier: { [results: 3] })

    List items = [itemA, itemB, itemC]

    Voting voting = new Voting(items: items)

    List votes = []

    void 'can tally and aggregate the results for multiple VotingItems'() {
        when:
        Map allResults = voting.tally([])

        then:
        allResults == [A: 1, B: 2, C: 3]
    }
}

