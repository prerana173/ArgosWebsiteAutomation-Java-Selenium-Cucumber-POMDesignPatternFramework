Argos Automation Assessment

Table of Contents
- [Introduction]
- [Features]
- [Installation]
- [Usage]

Introduction
This automation framework is written in Java Selenium with Cucumber BDD framework and POM design pattern. 
It has scenarios written in Gherkin format and underlying step definition implementation and pages for each of the web pages.
Application Automated - Argos Website.

Features
- 2 feature files written in slightly different Gherkin format.
- Separate step definitions and pages maintained for each page under test.
- Page classes contain objects and methods which are called from step definition.
- Tags and Hooks implemented. 
- Setup done to Skip the test cases and rerun failed test cases (using tags, hooks and testrunner file).
- Separate config/ properties file maintained.
- Cucumber report, Extent Report and pdf format report implemented. (Folders - pdf-test-output and spark-test-output)
- Setup done to capture screenshots on test case failure.
- Parallel execution possible at feature level as junit is used and not testng.
- Scripts can be executed from Cucumber file, Test runner file or maven.

Installation
To use this automation project, follow these steps:

Pre-Requisite: Eclipse, Maven should be installed on the machine.

1. Clone the repository: Clone the repository from github.
2. Install dependencies: Go to pom.xml and load the dependencies mentioned in there. 

Usage
To execute the automation scripts please see below:

Here's a brief overview of common usage scenarios:

1. Update the thread count in pom file to execute features files in parallel. 
2. Maven commands to run the project:
	a. mvn verify
	b. mvn test -DCucumber.options="--tags 'not @Skip'" //Command which includes sending tags via command line.
