package voting.selection

import spock.lang.Shared
import voting.Selection


trait CandidateFixture {

    @Shared
    Candidate z = new Candidate('Z')

    @Shared
    Candidate y = new Candidate('Y')

    @Shared
    Candidate x = new Candidate('X')

    @Shared
    Candidate w = new Candidate('W')

    List candidates = [z, y, x, w]

    Selection selection = new CandidateSelection(selection: candidates)

}
