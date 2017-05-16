# voting
An exploration (for now) of voting systems for Java8 and the Groovy language.


The build system is [Gradle](https://gradle.org/).

Install JDK8, set the JAVA_HOME global environment variable to point to it, and put the **bin** folder on your PATH.

## 2017-05-13 ##
**NOTE:** This project has been converted to a multi-module Gradle build, with currently 1 sub-project, **voting-core**.

You can run the build/tests/codeQuality and generate API documentation from your shell with:
* **Unix:** ./gradlew build
* **Windows:** gradlew build

This will:
* Download and install the correct version of Gradle if it's not already installed
* Download and cache project dependencies that are not already available
* Clean and Compile the source code
* Gather code quality metrics, using [Codenarc](http://codenarc.sourceforge.net/)
* Run all the tests, implemented with [Spock Framework](http://spockframework.org/)
* Gather test code coverage metrics, using [Jacoco](http://www.eclemma.org/jacoco/)
* Generate HTML code quality and test reports
* Generate HTML API documentation
* Build a **voting-core.jar** file

NOTE: The build will stop if there were failures at any step

The build output can be found in the **voting-core/build** folder:
* **voting-core.jar** library file in **libs** folder
* HTML code quality reports in **reports/codenarc** folder
* HTML test reports in **reports/tests** folder
* HTML test reports in **docs** folder



# Voting Systems
The **highly readlble** test cases demonstrate some voting systems. Individual test specs can be run in any Java IDE or from command line.

## 2017-05-16 ##
Currently 5 voting systems can be found and shown to work in the Test classes:
* **WinPlaceShowVotingSpec** - voters can vote *Win*, *Place*, or *Show* to express preference for the top 3 **VotingItems** (**candidate**, or **budget**). 
    *Win* counts for 4 votes, *Place* counts for 2 votes, and *Show* counts 1 vote. The tests assert the Tally is correct for each item.
* **AverageNumberVotingSpec** - voters can vote on a number and the result is the average value of all the votes
* **SingleVoteCandidateVotingSpec** - voters can cast 1 vote for a candidate from a list of candidates
* **TwoVoteCandidateVotingSpec** - voters can cast 2 votes for a candidate(s) from a list of candidates
* **CappedTotalAverageNumberVotingSpec** - voters can vote on a number of items that are numbers and the result is the 
    possibly adjusted (so the total of ll items <= the cap) average value of all the votes capped balanced by the number of Voters; **essentially a simple budgeting system**
* Introduced **Tally** to replace use of **Map** for holding tally results
* Added support for JaCoCo code coverage

# TODO
* Rework Vote and Selection similar to Tally ?????
* Clarify and possibly rework the **Selection** concept
* Support more data-driven testing via spreadsheets
* Build a demo webapp to allow users to test voting systems by downloading a partially initialized spreadsheet, filling in the votes, and uploading the filled out spreadsheet and get back a report of the **Tally**



