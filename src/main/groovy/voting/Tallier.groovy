package voting

/**
 * Computes results for a VotingItem based on the Votes.
 *
 */
@FunctionalInterface
interface Tallier {

    /**
     * Tallies Votes to produce a Map containing tally results.
     *
     * @param Votes all the votes
     *
     * @return Map containing results under the key "results"
     */
    Map tally(Iterable<Vote> votes)

}
