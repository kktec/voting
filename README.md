# voting
An exploration (for now) of voting systems for Java8 and the Groovy language.


The build system is [Gradle](https://gradle.org/).

Install JDK8, set the JAVA_HOME global environment variable to point to it, and put the **bin** folder on your PATH.

You can then run the build/tests/codeQuality and generate API documentation from your shell with:
* **Unix:** ./gradlew build
* **Windows:** gradlew build

This will:
* Download and install the correct version of Gradle if it's not already installed
* Download project dependencies that are not already available
* Clean and Compile the source code
* Gather code quality metrics using [Codenarc](http://codenarc.sourceforge.net/)
* Run the tests implemented with [Spock Framework](http://spockframework.org/)
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
## 2017-04-25 ##
Currently 3 voting systems can be found and shown to work in the Test classes:
* **AverageNumberVotingSpec** - voters can vote on a number and the result is the average value of all the votes
* **SingleVoteCandidateVotingSpec** - voters can cast 1 vote for a candidate from a list of candidates
* **TwoVoteCandidateVotingSpec** - voters can cast 2 votes for a candidate(s) from a list of candidates




