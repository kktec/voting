package voting

import voting.tallying.MappingTally
import voting.tallying.NumberTally

import voting.tallying.Tally

import spock.lang.Specification
import spock.lang.Unroll
import voting.selection.NumberSelection
import voting.tallying.AverageNumberTallier

@SuppressWarnings(['CyclomaticComplexity', 'LineLength'])
class CappedAverageNumberVotingSpec extends Specification {

    static final NONE

    VotingItem beer = createItem('beer')

    VotingItem hats = createItem('hats')

    VotingItem snacks = createItem('snacks')

    CappedAverageNumberVoting voting = new CappedAverageNumberVoting(
            id: 'CANV',
            title: 'Party Budget Voting',
            description: "We have money to spend. Let's have us some fun !",
            items: [beer, hats, snacks],
            cap: 100
    )

    @Unroll
    void 'can tally the item budget with voting scenario #scenario total #total with multiplier #adjustmentFactor'() {

        when: 'Voting on budget items'
        vote(beer, 'a', aBeer)
        vote(snacks, 'a', aSnacks)
        vote(hats, 'a', aHats)
        vote(beer, 'b', bBeer)
        vote(snacks, 'b', bSnacks)
        vote(hats, 'b', bHats)
        vote(beer, 'c', cBeer)
        vote(snacks, 'c', cSnacks)
        vote(hats, 'c', cHats)

        and: 'the tally is the capped and redistributed average '
        Tally votingTally = voting.tally()

        then:
        near votingTally.adjustmentFactor?.doubleValue(), adjustmentFactor?.doubleValue()
        near tallyValue(this.beer.id, votingTally), beerTally.doubleValue()
        near tallyValue(this.snacks.id, votingTally), snacksTally.doubleValue()
        near tallyValue(this.hats.id, votingTally), hatsTally.doubleValue()
        votingTally.total <= voting.cap

        where:
        scenario     | aBeer | bBeer | cBeer | aSnacks | bSnacks | cSnacks | aHats | bHats | cHats || beerTally || snacksTally || hatsTally || total || adjustmentFactor
        'NONE'       | NONE  | NONE  | NONE  | NONE    | NONE    | NONE    | NONE  | NONE  | NONE  || 0         || 0           || 0         || 0     || NONE
        'ONE UNDER'  | NONE  | NONE  | NONE  | 50      | NONE    | NONE    | NONE  | NONE  | NONE  || 0         || 50          || 0         || 50    || NONE
        'ONE OVER'   | 200   | NONE  | NONE  | NONE    | NONE    | NONE    | NONE  | NONE  | NONE  || 100       || 0           || 0         || 100   || 0.5
        'ALL AT CAP' | 25    | 50    | 0     | 25      | 25      | 100     | 50    | 25    | 0     || 25        || 50          || 25        || 100   || NONE
        'CAPPED'     | 25    | 50    | 75    | 50      | 100     | 75      | 50    | 75    | 100   || 25        || 37.5        || 37.5      || 100   || 0.5
        'EXTREME'    | 100   | 0     | 0     | 100     | 0       | 0       | 100   | 0     | 0     || 33.333    || 33.333      || 33.333    || 100   || 0.0
    }

    private boolean near(Double v1, Double v2) {
        Double error = 0.001
        Math.abs((v1 ?: 0d) - (v2 ?: 0d)) <= error
    }

    private Double tallyValue(String itemId, MappingTally votingTally) {
        Map tallies = votingTally.tallyMap() ?: votingTally
        NumberTally numberTally = tallies?.get(itemId) ?: new NumberTally(0d)
        numberTally.doubleValue()
    }


    private vote(VotingItem item, String voterId, Double vote) {
        if (vote != NONE) {
            item.vote(new Vote(voter: new Voter(voterId), vote: [results: vote]))
        }
    }

    private VotingItem createItem(String title) {
        new VotingItem(
                id: title,
                title: 'title',
                selection: new NumberSelection(),
                tallier: new AverageNumberTallier()
        )
    }
}
