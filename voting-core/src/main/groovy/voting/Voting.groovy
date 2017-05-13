package voting

import voting.tallying.MappingTally
import voting.tallying.Talliable
import voting.tallying.Tally

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
    Tally tally() { tallyItems() }

    protected final Tally tallyItems() {
        Map tally = items.collectEntries { item ->
            [(item.id): item.tally()]
        }
        new MappingTally(tally)
    }
}
