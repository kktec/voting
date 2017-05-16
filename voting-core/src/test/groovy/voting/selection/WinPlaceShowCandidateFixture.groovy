package voting.selection

import spock.lang.Shared
import voting.Vote
import voting.Voter
import voting.VotingItemFixture

trait WinPlaceShowCandidateFixture extends VotingItemFixture {

    @Shared
    Candidate z = new Candidate('Z')

    @Shared
    Candidate y = new Candidate('Y')

    @Shared
    Candidate x = new Candidate('X')

    @Shared
    Candidate w = new Candidate('W')

    @Shared
    Candidate v = new Candidate('V')

    @Shared
    Candidate u = new Candidate('U')

    Set candidates = [z, y, x, w, v, u]

    Selection selection = new WinPlaceShowSelection(candidates: candidates)

    def tally(Candidate candidate) {
        item.tally().results[candidate.id] ?: 0
    }

    void vote(String voterId, Candidate candidate, Integer winPlaceShow = 0) {
        if (candidate != null) {
            item.vote(new Vote(voter: new Voter(voterId), vote: [results: [(candidate.id): winPlaceShow]]))
        }
    }
}
