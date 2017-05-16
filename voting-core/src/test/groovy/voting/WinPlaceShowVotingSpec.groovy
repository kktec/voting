package voting


import spock.lang.Specification
import spock.lang.Unroll
import voting.selection.WinPlaceShowCandidateFixture
import voting.tallying.WinPlaceShowTallier


@SuppressWarnings(['CyclomaticComplexity', 'LineLength'])
class WinPlaceShowVotingSpec extends Specification implements WinPlaceShowCandidateFixture {

    final static Integer WIN = 4
    final static Integer PLACE = 2
    final static Integer SHOW = 1

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
    void 'Win Place and Show votes per voter voting scenario #scenario Tally is Z:#tallyZ Y:#tallyY X:#tallyX W:#tallyW V:#tallyV U:#tallyU'() {
        when: 'voting WIN PLACE or SHOW on a Candidate'
        vote('a', aWin, WIN)
        vote('a', aPlace, PLACE)
        vote('b', aShow, SHOW)
        vote('b', bWin, WIN)
        vote('b', bPlace, PLACE)
        vote('b', bShow, SHOW)
        vote('c', cWin, WIN)
        vote('c', cPlace, PLACE)
        vote('c', cShow, SHOW)
        vote('d', dWin, WIN)
        vote('d', dPlace, PLACE)
        vote('d', dShow, SHOW)

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
