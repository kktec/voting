package voting.tallier

import voting.Tallier
import voting.Vote
import voting.selection.Candidate

/**
 * Tallies by simply counting Votes for a Candidate.
 */
class CandidateVoteCountingTallier implements Tallier {

    List<Candidate> candidates = []

    @Override
    Map tally(Iterable<Vote> votes) {
        Map results = candidates.collectEntries { Candidate candidate ->
            [(candidate.id): 0]
        }
        List voting = votes.vote.results
        voting.each { Candidate candidate ->
            results[(candidate.id)] += 1
        }
        [results: results]
    }
}
