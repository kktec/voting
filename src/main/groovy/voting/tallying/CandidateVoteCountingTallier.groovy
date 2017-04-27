package voting.tallying

import voting.Vote
import voting.selection.Candidate

/**
 * Tallies by simply counting Votes for a Candidate.
 */
class CandidateVoteCountingTallier implements Tallier {

    List<Candidate> candidates = []

    @Override
    Map tally(Iterable<Vote> votes) {
        Map tally = tallyWithNoVotes()
        List voting = votes.vote.results
        voting.each { Candidate candidate ->
            tally[candidate.id] += 1
        }
        [results: tally]
    }

    private Map tallyWithNoVotes() {
        candidates.collectEntries { Candidate candidate ->
            [(candidate.id): 0]
        }
    }
}
