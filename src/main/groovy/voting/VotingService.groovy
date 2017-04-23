package voting

class VotingService {

    private final Map<String, Collection<Vote>> votes = [:]

    void vote(VotingItem votingItem, Vote vote) {
        Collection votesForItem = votes[votingItem] ?: []
        votesForItem << vote
        votes[votingItem] = votesForItem
    }

    Map tally(VotingItem votingItem) {
        Collection votesForItem = votes[votingItem] ?: []
        votingItem.tally(votesForItem)
     }
}
