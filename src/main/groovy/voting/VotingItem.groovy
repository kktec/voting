package voting

class VotingItem {

    String id

    String title

    String description

    Selection selection

    @Delegate
    Tallier tallier
}
