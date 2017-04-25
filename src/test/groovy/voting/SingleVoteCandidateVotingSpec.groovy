package voting

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import voting.selection.Candidate
import voting.selection.CandidateSelection

@SuppressWarnings('CyclomaticComplexity')
class SingleVoteCandidateVotingSpec extends Specification {

    @Shared
    Candidate z = new Candidate('Z')

    @Shared
    Candidate y = new Candidate('Y')

    @Shared
    Candidate x = new Candidate('X')

    @Shared
    Candidate w = new Candidate('W')

    List candidates = [z, y, x, w]

    Selection selection = new CandidateSelection(selection: candidates)

    Tallier tallier = { Iterable<Vote> votes ->
        Map results = candidates.collectEntries { Candidate candidate ->
            [(candidate): 0]
        }
        List voting = votes.vote.results
        voting.each { Candidate candidate ->
            results[(candidate)] += 1
        }
        [results: results]
    }

    VotingItem item = new VotingItem(
            title: 'An Election',
            description: 'Choose 1 candidate',
            selection: selection,
            tallier: tallier
    )

    VotingService votingService = new VotingService()

    @Unroll()
    def 'One vote per voter voting scenario #scenario Tally is Z:#tallyZ Y:#tallyY X:#tallyX W:#tallyW '() {
        when: 'voting on a Candidate'
        vote('a', aVote)
        vote('b', bVote)
        vote('c', cVote)
        vote('d', dVote)
        vote('e', eVote)

        then: 'the talley result is the number of voters per Candidate'
        tally(z) == tallyZ
        tally(y) == tallyY
        tally(x) == tallyX
        tally(w) == tallyW

        where:
        scenario   | aVote | bVote | cVote | dVote | eVote || tallyZ || tallyY || tallyX || tallyW
        'NONE'     | null  | null  | null  | null  | null  || 0      || 0      || 0      || 0
        'ONE'      | null  | null  | x     | null  | null  || 0      || 0      || 1      || 0
        'TWO'      | z     | null  | x     | null  | null  || 1      || 0      || 1      || 0
        'V1'       | z     | w     | x     | y     | z     || 2      || 1      || 1      || 1
        'V2'       | y     | w     | x     | y     | z     || 1      || 2      || 1      || 1
        'ALL SAME' | y     | y     | y     | y     | y     || 0      || 5      || 0      || 0
        'SOME'     | null  | w     | w     | null  | z     || 1      || 0      || 0      || 2
    }

    private tally(Candidate candidate) {
        votingService.tally(item).results[candidate]
    }


    private vote(String voterId, Candidate vote) {
        if (vote != null) {
            votingService.vote(item, new Vote(voter: new Voter(voterId), vote: [results: vote]))
        }
    }
}
