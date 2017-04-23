package voting

class VotingItem<S extends Selection> {

    String id

    String title

    String description

    Selection selection

    Map tally(votes) {
        Tallier tallier = selection.tallier
        tallier.tally(votes)
    }
}
