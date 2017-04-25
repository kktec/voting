package voting

/**
 * A group of VotingItems considered together.
 *
 */
class Voting implements Tallier {

    /** a unique id */
    String id

    String title

    String description

    List<VotingItem> items = []

    @Override
    final Map tally(Iterable<Vote> votes) {
        // TODO: produce the tallies for all the items
        Map results = items.collectEntries { item ->
            [(item.id): item.tally(votes).results]
        }
    }
}
