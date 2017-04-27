package voting


trait VotingItemFixture {

    abstract VotingItem create()

    VotingItem item = create()

}
