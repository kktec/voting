package voting

/**
 * An abstract representation of choice(s) a Voter will make when voting on a VotingItem.
 */
interface Selection {

    /**
     *
     * @return the type of the VotingItem choice
     *
     */
    Class getType()

    /**
     *
     * @return a selection Object of the Class specifed by #getType
     */
    def getSelection()
}
