package voting

import voting.tallying.MappingTally
import voting.tallying.NumberTally

import voting.tallying.Tally

import spock.lang.Specification
import spock.lang.Unroll
import voting.selection.NumberSelection
import voting.tallying.AverageNumberTallier

@SuppressWarnings(['CyclomaticComplexity', 'LineLength'])
class CappedTotalAverageNumberVotingSpec extends Specification {

    static final NONE

    VotingItem beer = createItem('beer')

    VotingItem hats = createItem('hats')

    VotingItem snacks = createItem('snacks')

    CappedTotalAverageNumberVoting voting = new CappedTotalAverageNumberVoting(
            id: 'CANV',
            title: 'Party Budget Voting',
            description: "We have money to spend. Let's have us some fun !.",
            items: [beer, hats, snacks],
            cap: 300,
            numberOfVoters: 3
    )

    @Unroll
    void 'can tally the item budget with voting scenario #scenario total #total with multiplier #adjustmentFactor'() {

        when: 'Everybody gets 3 votes on each budget item'
        vote(beer, 'a', aBeer)
        vote(snacks, 'a', aSnacks)
        vote(hats, 'a', aHats)
        vote(beer, 'b', bBeer)
        vote(snacks, 'b', bSnacks)
        vote(hats, 'b', bHats)
        vote(beer, 'c', cBeer)
        vote(snacks, 'c', cSnacks)
        vote(hats, 'c', cHats)

        and: 'the tally is the capped and balanced and redistributed average '
        Tally votingTally = voting.tally()

        then:
        votingTally.total < voting.cap || near(voting.cap, votingTally.total.doubleValue())
        near votingTally.adjustmentFactor?.doubleValue(), adjustmentFactor?.doubleValue()
        near tallyValue(this.beer.id, votingTally), beerTally.doubleValue()
        near tallyValue(this.snacks.id, votingTally), snacksTally.doubleValue()
        near tallyValue(this.hats.id, votingTally), hatsTally.doubleValue()

        where:
        scenario        | aBeer | bBeer | cBeer | aSnacks | bSnacks | cSnacks | aHats | bHats | cHats || beerTally || snacksTally || hatsTally || total || adjustmentFactor
        'NONE'          | 0     | 0     | 0     | 0       | 0       | 0       | 0     | 0     | 0     || 0         || 0           || 0         || 0     || 1
        'ONE'           | 0     | 0     | 0     | 1       | 0       | 0       | 0     | 0     | 0     || 0         || 300         || 0         || 300   || 300
        'ALL DEFAULT'   | 1     | 1     | 1     | 1       | 1       | 1       | 1     | 1     | 1     || 100       || 100         || 100       || 300   || 33.333
        'ALL DIFFERENT' | 2     | 1     | 0     | 2       | 2       | 2       | 1     | 1     | 1     || 75        || 150         || 75        || 300   || 25
        'ALL VETO HATS' | 2     | 1     | 0     | 2       | 2       | 2       | 0     | 0     | 0     || 100       || 200         || 0         || 300   || 33.333
        'A LITTLE HATS' | 2     | 1     | 0     | 2       | 2       | 2       | 0     | 0     | 1     || 90        || 180         || 30        || 300   || 30

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
