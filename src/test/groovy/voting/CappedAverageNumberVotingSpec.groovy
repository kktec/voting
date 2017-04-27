package voting

import static org.hamcrest.Matchers.closeTo

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
        Map results = voting.tally()

        then:
        closeTo results.adjustmentFactor, adjustmentFactor
        closeTo results[this.beer.id], beerTally
        results.total <= voting.cap

        where:
        scenario     | aBeer | bBeer | cBeer || beerTally | aSnacks | bSnacks | cSnacks || snacksTally | aHats | bHats | cHats || hatsTally || total | adjustmentFactor
        'NONE'       | NONE  | NONE  | NONE  || 0         | NONE    | NONE    | NONE    || 0           | NONE  | NONE  | NONE  || 0         || 0     | NONE
        'ONE UNDER'  | NONE  | NONE  | NONE  || 0         | 50      | NONE    | NONE    || 50          | NONE  | NONE  | NONE  || 0         || 50    | NONE
        'ALL AT CAP' | 25    | 50    | 0     || 25        | 25      | 25      | 100     || 50          | 50    | 25    | 0     || 25        || 100   | NONE
        'ONE OVER'   | NONE  | NONE  | NONE  || 0         | NONE    | NONE    | NONE    || 0           | 200   | NONE  | NONE  || 200       || 100   | 0.5
        'CAPPED'     | 25    | 50    | 75    || 50        | 25      | 25      | 100     || 50          | 50    | 25    | 75    || 150       || 100   | 2.0 / 3
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
