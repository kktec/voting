package voting.selection

import spock.lang.Shared
import voting.Selection
import voting.Vote
import voting.Voter
import voting.VotingItemFixture


trait CandidateFixture extends VotingItemFixture {

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

    def tally(Candidate candidate) {
        Map tally = item.tally().results ?: [:]
        tally[candidate.id] ?: 0
    }

    void vote(String voterId, Candidate vote) {
        if (vote != null) {
            item.vote(new Vote(voter: new Voter(voterId), vote: [results: vote]))
        }
    }
}
