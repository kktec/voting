package voting

import spock.lang.Specification
import spock.lang.Unroll

@SuppressWarnings(['UnnecessaryBooleanExpression', 'TrailingComma'])
class AverageNumberVotingSpec extends Specification {

    Selection selection = new NumberSelection()

    Tallier tallier = { Iterable votes ->
        List voting = votes.vote
        Double total = voting.value.sum()
        Double average = total != null ? total / votes.size() : null
        [value: average]
    }

    VotingItem item = new VotingItem(
            title: 'A Budget Item',
            description: 'Choose an amount',
            selection: selection,
            tallier: tallier
    )

    VotingService votingService = new VotingService()

    @Unroll()
    def 'Average Number voting scenario #scenario Average is #tally'() {

        when: 'voting on a Number'
        vote('a', aVote)
        vote('b', bVote)
        vote('c', cVote)
        vote('d', dVote)
        vote('e', eVote)

        then: 'the final talley is the average of all the votes'
        votingService.tally(item).value == tally

        where:
        scenario   | aVote | bVote | cVote | dVote | eVote || tally
        'NONE'     | null  | null  | null  | null  | null  || null
        'ONE'      | null  | null  | 24    | null  | null  || 24
        'ALL VETO' | 0     | 0     | 0     | 0     | 0     || 0
        'ONE VETO' | 30    | 0     | 30    | 30    | 30    || 24
        'V1'       | 20    | 100   | 0     | 20    | 10    || 30
        'V2'       | 22    | 95    | 1     | 28    | 13    || 31.8
        'EXTREME'  | 0     | 100   | 0     | 0     | 0     || 20
        'ALL SAME' | 20    | 20    | 20    | 20    | 20    || 20
        'SOME'     | 22    | null  | 24    | null  | 26    || 24
    }

    private vote(String voterId, Double vote) {
        if (vote != null) {
            votingService.vote(item, new Vote(voter: new Voter(voterId), vote: [value: vote]))
        }
    }
}
