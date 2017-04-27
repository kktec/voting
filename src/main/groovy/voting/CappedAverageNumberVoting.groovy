package voting

import voting.tallying.MappingTally
import voting.tallying.Tally


class CappedAverageNumberVoting extends Voting {

    Double cap

    @Override
    Tally tally() {
        Tally tally = tallyItems()
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
            Double adjustedTotal = adjustedTallies.values().sum() ?: 0
            new MappingTally([tally: adjustedTallies, total: adjustedTotal, adjustmentFactor: adjustmentFactor])
        } else { tally }
    }
}
