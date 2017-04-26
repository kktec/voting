package voting

import spock.lang.Unroll
import voting.selection.CandidateFixture
import voting.tallier.CandidateVoteCountingTallier

class SingleVoteCandidateVotingSpec extends CandidateVotingSpec implements CandidateFixture {

    @Override
    VotingItem create() {
        new VotingItem(
                title: 'An Election',
                description: 'Choose 1 candidate',
                selection: selection,
                tallier: new CandidateVoteCountingTallier(candidates: candidates)
        )
    }

    @Unroll()
    def 'One vote per voter voting scenario #scenario Tally is Z:#tallyZ Y:#tallyY X:#tallyX W:#tallyW '() {
        when: 'voting on a Candidate'
        vote('a', aVote)
        vote('b', bVote)
        vote('c', cVote)
        vote('d', dVote)
        vote('e', eVote)

        then: 'the talley result is the number of votes per Candidate'
        tally(z) == tallyZ
        tally(y) == tallyY
        tally(x) == tallyX
        tally(w) == tallyW

        where:
        scenario   | aVote | bVote | cVote | dVote | eVote || tallyZ || tallyY || tallyX || tallyW
        'NONE'     | null  | null  | null  | null  | null  || 0      || 0      || 0      || 0
        'ONE'      | null  | null  | x     | null  | null  || 0      || 0      || 1      || 0
        'TWO'      | z     | null  | x     | null  | null  || 1      || 0      || 1      || 0
        'Sa'       | z     | w     | x     | y     | z     || 2      || 1      || 1      || 1
        'Sb'       | y     | w     | x     | y     | z     || 1      || 2      || 1      || 1
        'ALL SAME' | y     | y     | y     | y     | y     || 0      || 5      || 0      || 0
        'SOME'     | null  | w     | w     | null  | z     || 1      || 0      || 0      || 2
    }
}
