package voting

import spock.lang.Specification
import spock.lang.Unroll
import voting.selection.CandidateFixture
import voting.tallying.CandidateVoteCountingTallier

@SuppressWarnings(['CyclomaticComplexity'])
class TwoVoteCandidateVotingSpec extends Specification implements CandidateFixture {

    @Override
    VotingItem create() {
        new VotingItem(
                title: 'An Election',
                description: 'You have 2 votes to apply to Candidates. You may apply both Votes to a single Candidate',
                selection: selection,
                tallier: new CandidateVoteCountingTallier(candidates: candidates)
        )
    }

    @Unroll()
    void 'Two votes per voter voting scenario #scenario Tally is Z:#tallyZ Y:#tallyY X:#tallyX W:#tallyW '() {
        when: 'voting on a Candidate'
        vote('a', aVote1)
        vote('a', aVote2)
        vote('b', bVote1)
        vote('b', bVote2)
        vote('c', cVote1)
        vote('c', cVote2)

        then: 'the talley result is the number of votes per Candidate'
        tally(z) == tallyZ
        tally(y) == tallyY
        tally(x) == tallyX
        tally(w) == tallyW

        where:
        scenario   | aVote1 | aVote2 | bVote1 | bVote2 | cVote1 | cVote2 || tallyZ || tallyY || tallyX || tallyW
        'NONE'     | null   | null   | null   | null   | null   | null   || 0      || 0      || 0      || 0
        'ONE'      | null   | null   | x      | null   | null   | null   || 0      || 0      || 1      || 0
        'TWO'      | z      | null   | x      | null   | null   | null   || 1      || 0      || 1      || 0
        'Sa'       | z      | w      | x      | y      | z      | w      || 2      || 1      || 1      || 2
        'Sb'       | y      | w      | x      | y      | z      | x      || 1      || 2      || 2      || 1
        'ALL SAME' | y      | y      | y      | y      | y      | y      || 0      || 6      || 0      || 0
        'SOME'     | null   | w      | w      | null   | z      | z      || 2      || 0      || 0      || 2
    }
}
