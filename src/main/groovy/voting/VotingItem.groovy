package voting

/**
 * A unique item that provides information that Voters can use to make a Selection (vote) on and a means (Tallier) to determine the Voters preference(s).
 */
class VotingItem implements Tallier {

    /** a unique id */
    String id

    String title

    String description

    Selection selection

    @Delegate
    Tallier tallier

    @Override
    String toString() { id.toString() }
}
