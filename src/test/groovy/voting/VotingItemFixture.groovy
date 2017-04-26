package voting


trait VotingItemFixture {

    abstract VotingItem create()

    VotingService votingService = new VotingService()

    VotingItem item = create()

}
