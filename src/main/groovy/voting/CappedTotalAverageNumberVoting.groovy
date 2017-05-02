package voting

import voting.tallying.MappingTally
import voting.tallying.NumberTally
import voting.tallying.Tally


class CappedTotalAverageNumberVoting extends Voting {

    Double cap

    Integer numberOfVoters

    @Override
    Tally tally() {
        Tally tallyItems = tallyItems()
        Double total = tallyItems.values().tally.sum() * numberOfVoters ?: 0d
        Double adjustmentFactor = (total == 0d) ? 1d: cap / total
        Map adjustedTallies = tallyItems.collectEntries { String itemId, Number tally ->
            Double itemTally = tally?.doubleValue() * numberOfVoters ?: 0d
            Tally adjusted = new NumberTally(itemTally * adjustmentFactor)
            [(itemId), adjusted]
        }

        new MappingTally(
                tally: new MappingTally(adjustedTallies),
                total: new NumberTally(total),
                adjustmentFactor: new NumberTally(adjustmentFactor)
        )
    }
}
