package voting.tallying

interface Talliable {

    /**
     * Tallies Votes to produce a Map containing tally results.
     *
     * @return Map containing results under the key "results"
     */
    Map tally()

}
