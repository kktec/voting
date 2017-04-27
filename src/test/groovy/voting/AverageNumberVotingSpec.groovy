package voting

import spock.lang.Specification
import spock.lang.Unroll
import voting.selection.NumberSelection
import voting.tallying.AverageNumberTallier

@SuppressWarnings(['CyclomaticComplexity'])
class AverageNumberVotingSpec extends Specification implements VotingItemFixture {

    @Override
    VotingItem create() {
        new VotingItem(
                title: 'A Budget Item',
                description: 'Choose an amount',
                selection: new NumberSelection(),
                tallier: new AverageNumberTallier()
        )
    }

    @Unroll()
    void 'Average Number voting scenario #scenario Average is #tally'() {

        when: 'voting on a Number'
        vote('a', aVote)
        vote('b', bVote)
        vote('c', cVote)
        vote('d', dVote)
        vote('e', eVote)

        then: 'the talley result is the average of all the votes'
        item.tally() == tally

        where:
        scenario   | aVote | bVote | cVote | dVote | eVote || tally
        'NONE'     | null  | null  | null  | null  | null  || 0
        'ONE'      | null  | null  | 24    | null  | null  || 24
        'ALL VETO' | 0     | 0     | 0     | 0     | 0     || 0
        'ONE VETO' | 30    | 0     | 30    | 30    | 30    || 24
        'V1'       | 20    | 100   | 0     | 20    | 10    || 30
        'V2'       | 22    | 95    | 1     | 28    | 13    || 31.8d
        'EXTREME'  | 0     | 100   | 0     | 0     | 0     || 20
        'ALL SAME' | 20    | 20    | 20    | 20    | 20    || 20
        'SOME'     | 22    | null  | 24    | null  | 26    || 24
    }

    private vote(String voterId, Double vote) {
        if (vote != null) {
            item.vote(new Vote(voter: new Voter(voterId), vote: [results: vote]))
        }
    }
}
