package voting

import spock.lang.Specification
import voting.selection.Candidate

abstract class CandidateVotingSpec extends Specification implements VotingItemFixture {

    protected tally(Candidate candidate) {
        votingService.tally(item).results[candidate]
    }

    protected vote(String voterId, Candidate vote) {
        if (vote != null) {
            votingService.vote(item, new Vote(voter: new Voter(voterId), vote: [results: vote]))
        }
    }
}
