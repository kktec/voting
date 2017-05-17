package voting


import spock.lang.Specification
import spock.lang.Unroll
import voting.selection.WinPlaceShowCandidateFixture
import voting.tallying.WinPlaceShowTallier


@SuppressWarnings(['CyclomaticComplexity', 'LineLength'])
class WinPlaceShowVotingSpec extends Specification implements WinPlaceShowCandidateFixture {

    Integer win
    Integer place = 2
    Integer show = 1

    @Override
    VotingItem create() {
        new VotingItem(
                title: 'A Win-Place-Show Selection',
                description: 'Select your top 3 Candidates in order of preference.',
                selection: selection,
                tallier: new WinPlaceShowTallier(candidates: candidates)
        )
    }

    @Unroll
    void 'Win3 Place2 and Show1 votes per voter voting scenario #scenario Tally is Z:#tallyZ Y:#tallyY X:#tallyX W:#tallyW V:#tallyV U:#tallyU'() {
        given:
        win = 3

        when: 'voting WIN PLACE or SHOW on a Candidate'
        vote('a', aWin, win)
        vote('a', aPlace, place)
        vote('b', aShow, show)
        vote('b', bWin, win)
        vote('b', bPlace, place)
        vote('b', bShow, show)
        vote('c', cWin, win)
        vote('c', cPlace, place)
        vote('c', cShow, show)
        vote('d', dWin, win)
        vote('d', dPlace, place)
        vote('d', dShow, show)

        then: 'the talley result is sum of the number of Win4 Place3 or Show1 votes per Candidate'
        tally(z) == tallyZ
        tally(y) == tallyY
        tally(x) == tallyX
        tally(w) == tallyW
        tally(v) == tallyV
        tally(u) == tallyU

        where:
        scenario  | aWin | aPlace | aShow | bWin | bPlace | bShow | cWin | cPlace | cShow | dWin | dPlace | dShow || tallyZ || tallyY || tallyX || tallyW || tallyV || tallyU
        'NONE'    | null | null   | null  | null | null   | null  | null | null   | null  | null | null   | null  || 0      || 0      || 0      || 0      || 0      || 0
        'ONE'     | z    | x      | u     | null | null   | null  | null | null   | null  | null | null   | null  || 3      || 0      || 2      || 0      || 0      || 1
        'ALL'     | z    | x      | u     | y    | w      | v     | v    | u      | x     | v    | z      | y     || 5      || 4      || 3      || 2      || 7      || 3
    }

    @Unroll
    void 'Win4 Place2 and Show1 votes per voter voting scenario #scenario Tally is Z:#tallyZ Y:#tallyY X:#tallyX W:#tallyW V:#tallyV U:#tallyU'() {
        given:
        win = 4

        when: 'voting WIN PLACE or SHOW on a Candidate'
        vote('a', aWin, win)
        vote('a', aPlace, place)
        vote('b', aShow, show)
        vote('b', bWin, win)
        vote('b', bPlace, place)
        vote('b', bShow, show)
        vote('c', cWin, win)
        vote('c', cPlace, place)
        vote('c', cShow, show)
        vote('d', dWin, win)
        vote('d', dPlace, place)
        vote('d', dShow, show)

        then: 'the talley result is sum of the number of Win4 Place3 or Show1 votes per Candidate'
        tally(z) == tallyZ
        tally(y) == tallyY
        tally(x) == tallyX
        tally(w) == tallyW
        tally(v) == tallyV
        tally(u) == tallyU

        where:
        scenario  | aWin | aPlace | aShow | bWin | bPlace | bShow | cWin | cPlace | cShow | dWin | dPlace | dShow || tallyZ || tallyY || tallyX || tallyW || tallyV || tallyU
        'NONE'    | null | null   | null  | null | null   | null  | null | null   | null  | null | null   | null  || 0      || 0      || 0      || 0      || 0      || 0
        'ONE'     | z    | x      | u     | null | null   | null  | null | null   | null  | null | null   | null  || 4      || 0      || 2      || 0      || 0      || 1
        'ALL'     | z    | x      | u     | y    | w      | v     | v    | u      | x     | v    | z      | y     || 6      || 5      || 3      || 2      || 9      || 3
    }
}
