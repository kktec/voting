package voting

import voting.tallying.Talliable
import voting.tallying.Tallier
import voting.tallying.Tally

/**
 * A unique item that provides information that Voters can use to make a Selection (vote)
 * and a means (Tallier) to determine the Voters preference(s).
 */
class VotingItem implements Talliable {

    private final Collection<Vote> votes = []

    /** a unique id */
    String id

    String title

    String description

    Selection selection

    Tallier tallier

    Tally currentTally

    @Override
    Tally tally() {
        currentTally = tallier.tally(votes)
    }

    final void vote(Vote vote) { votes << vote }

    @Override
    String toString() { title.toString() }
}
