package voting.tallying

import voting.Vote

/**
 * Tallies by computing the average value of each number Vote.
 */
class AverageNumberTallier implements Tallier {

    @Override
    Map tally(Iterable<Vote> votes) {
        List voting = votes.vote.results
        Double total = voting.sum()
        Double average = total != null ? total / votes.size() : 0
        [results: average]
    }
}
