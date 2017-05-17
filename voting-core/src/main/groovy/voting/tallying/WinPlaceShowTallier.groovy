package voting.tallying

import voting.Vote
import voting.selection.Candidate

class WinPlaceShowTallier implements Tallier {

    Set<Candidate> candidates = []

    @Override
    Tally tally(Iterable<Vote> votes) {
        Map tally = tallyWithNoVotes()
        List voting = votes.vote.results
        voting.each { Map map ->
            map.each { String candidateId, Number winPlaceShow ->
                tally[candidateId] = tally[candidateId] += winPlaceShow ?: 0
            }
        }
        new MappingTally([results: tally])
    }

    private Map tallyWithNoVotes() {
        candidates.collectEntries { Candidate candidate ->
            [(candidate.id): 0]
        }
    }
}
