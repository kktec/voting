package voting.selection

import groovy.transform.Canonical

@Canonical
class CandidateSelection implements Selection {

    static final int DEFAULT_VOTES_PER_VOTER = 1

    final Class type = List

    Integer votesPerVoter = DEFAULT_VOTES_PER_VOTER

    List selection = []
}
