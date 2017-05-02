package voting

import voting.tallying.MappingTally
import voting.tallying.NumberTally
import voting.tallying.Tally


class CappedAverageNumberVoting extends Voting {

    Double cap

    @Override
    Tally tally() {
        Tally tallyItems = tallyItems()
        Double total = tallyItems.values().tally.sum() ?: 0d

        Double adjustmentFactor
        if (total > cap) {
            adjustmentFactor = cap / total
            Map adjustedTallies = tallyItems.collectEntries {
                String itemId = it.key
                Double itemTally = it.value?.doubleValue() ?: 0d
                Tally adjusted = new NumberTally(itemTally * adjustmentFactor)
                [(itemId), adjusted]
            }
            Double adjustedTotal = adjustedTallies.values().tally.sum() ?: 0d
            new MappingTally(
                    tally: new MappingTally(adjustedTallies),
                    total: new NumberTally(adjustedTotal),
                    adjustmentFactor: new NumberTally(adjustmentFactor)
            )
        } else { tallyItems }
    }
}
