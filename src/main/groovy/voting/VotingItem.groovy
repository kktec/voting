package voting

class VotingItem {

    String id

    String title

    String description

    Selection selection

    Tallier tallier

    Map tally(votes) {
       tallier.tally(votes)
    }
}
