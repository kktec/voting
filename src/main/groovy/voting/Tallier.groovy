package voting

@FunctionalInterface
interface Tallier {

    Map tally(Iterable<Vote> votes)

}
