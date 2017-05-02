# voting
An exploration (for now) of voting systems for Java8 and the Groovy language.


The build system is [Gradle](https://gradle.org/).

Install JDK8, set the JAVA_HOME global environment variable to point to it, and put the **bin** folder on your PATH.

You can then run the build/tests/codeQuality and generate API documentation from your shell with:
* **Unix:** ./gradlew build
* **Windows:** gradlew build

This will:
* Download and install the correct version of Gradle if it's not already installed
* Download and cache project dependencies that are not already available
* Clean and Compile the source code
* Gather code quality metrics, using [Codenarc](http://codenarc.sourceforge.net/)
* Run alllthe tests, implemented with [Spock Framework](http://spockframework.org/)
* Generate HTML code quality and test reports
* Generate HTML API documentation
* Build a **voting.jar** file

NOTE: The build will stop if there were failures at any step

The build output can be found in the **build** folder:
* **voting.jar** library file in **libs** folder
* HTML code quality reports in **reports/codenarc** folder
* HTML test reports in **reports/tests** folder
* HTML test reports in **docs** folder



# Voting Systems
The **highly readlble** test cases demonstrate some voting systems. Individual test specs can be run in any Java IDE.

## 2017-05-02 ##
Currently 4 voting systems can be found and shown to work in the Test classes:
* **AverageNumberVotingSpec** - voters can vote on a number and the result is the average value of all the votes
* **SingleVoteCandidateVotingSpec** - voters can cast 1 vote for a candidate from a list of candidates
* **TwoVoteCandidateVotingSpec** - voters can cast 2 votes for a candidate(s) from a list of candidates
* **CappedTotalAverageNumberVotingSpec** - voters can vote on a number of items that are numbers and the result is the 
    possibly adjusted (so the total of ll items <= the cap) average value of all the votes capped balanced by the number of Voters; **essentially a simple budgeting system**  

* Introduced **Tally** to replace use of **Map** for holding tally results
* Added support for JaCoCo code coverage

# TODO
* Rework Vote and Selection similar to Tally
* Clarify and possibly rework the **Selection** concept
* Add more tests for **CappedAverageNumberVotingSpec**
* Support more data-driven testing via spreadsheets
* Build a demo webapp to allow users to test voting sytems by downloading a partially initialized spreadsheet, filling in the votes, and uploading the filled out spreadsheet and get back a report of the **Tally**



