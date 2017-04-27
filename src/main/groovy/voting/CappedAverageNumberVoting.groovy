package voting


class CappedAverageNumberVoting extends Voting {

    Double cap

    @Override
    Map tally() {
        Map tally = tallyItems().results
        Double total = tally.values().sum() ?: 0

        Double adjustmentFactor
        if (total > cap) {
            adjustmentFactor = cap / total
            Map adjustedTallies = tally.collectEntries {
                String itemId = it.key
                Double itemTally = it.value
                Double adjusted = itemTally * adjustmentFactor
                [(itemId), adjusted]
            }
            [results: adjustedTallies, total: adjustedTallies.values().sum() ?: 0, adjustmentFactor: adjustmentFactor]
        } else { tally }
    }
}
