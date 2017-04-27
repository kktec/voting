package voting

import voting.tallying.Talliable

/**
 * A group of VotingItems considered together.
 *
 */
class Voting implements Talliable {

    /** a unique id */
    String id

    String title

    String description

    List<VotingItem> items = []

    @Override
    Map tally() { tallyItems() }

    protected final Map tallyItems() {
        Map results = items.collectEntries { item ->
            [(item.id): item.tally().results]
        }
        [results: results]
    }
}
