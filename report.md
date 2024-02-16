# Report for assignment 3

This is a template for your report. You are free to modify it as needed.
It is not required to use markdown for your report either, but the report
has to be delivered in a standard, cross-platform format.

## Project

Name: Karate-core (only) group 8 

URL: https://github.com/karatelabs/karate/tree/master/karate-core

### Description

this project combines various features into one project such as API-test automation, mocks, performance testing and also UI automation, but we are only going to consider the karate-core part of the project

## Onboarding experience

In general, the onboarding experience with this project was really good everything worked from the get-go, and there were no problems with the installation of dependencies, compiling the project or running the tests. However, the project lacked documentation on the distinction between the users who are going to use the project and those who want to contribute to the project.

### Did you have to install a lot of additional tools to build the software?

No we didn't have to install any additional tool expect maven to both build and and run test for the project

### Were those tools well documented?

Since the only tools required was essentially maven and maven has great documentation of how to use it, so yes we would say that the tools were well documentated 

### Were other components installed automatically by the build script?

Yes there were lot of dependencies defined in the pom.xml in the karate-core project and these were automatically installed using maven

### Did the build conclude automatically without errors?

Yes the build just worked without any problem 

### How well do examples and tests run on your system(s)?

The test ran without any problems and failures, and we didn't have any problems with running the provided examples which are on the /examples folder

## Complexity

### the 5 functions with high cyclomatic complexity number (ccn) 

#### done by: Mert Demirsü
1. ServerContext::getMember@532-606@./src/main/java/com/intuit/karate/http/ServerContext.java
2. Request::getMember@531-597@./src/main/java/com/intuit/karate/http/Request.java

#### done by: Melissa Mazura
4. HttpRequestBuilder::getMember@610-646@./src/main/java/com/intuit/karate/http/HttpRequestBuilder.java
5. HttpRequestBuilder::buildInternal@167-235@./src/main/java/com/intuit/karate/http/HttpRequestBuilder.java

#### done by: Marcus Odin
7. JsonUtils::recurseJsonString@282-355@./src/main/java/com/intuit/karate/JsonUtils.java
8. ScenarioEngine::recurseEmbeddedExpressions@1430-1503@./src/main/java/com/intuit/karate/core/ScenarioEngine.java

#### done by: Atheer Salim
10. ScenarioEngine::evalKarateExpression@2160-2240@./src/main/java/com/intuit/karate/core/ScenarioEngine.java
11. ScenarioIterator::tryAdvance@68-166@./src/main/java/com/intuit/karate/core/ScenarioIterator.java

#### done by: Jonatan Tuvstedt
13. ScenarioEngine::match@1788-1843@./src/main/java/com/intuit/karate/core/ScenarioEngine.java
    1. When comparing the manual count (CC of 20) to that of Lizard (CC of 21) they are almost the same, being one different. I found the calculation to be quite clearcut so maybe Lizard has a slightly different formula than the one in the slides counting `if`, `while`, `||`, `&&`, `return` and `throw`, but I didn't manage to find how the count their CC.
        - if = 9
        - || = 5
        - && = 6
        - return = 2
        - throw = 0
    2. The function is not very long, having a NLOC of only 46. In fact most of the CC comes from just 2 very long if statements with 6 respectively 7 predicates.
    3. As there is no function level documentation for this function discerning the purpose is not super easy. The general purpose seems to be a step in the process of matching a string of a specific type to a expected result. This purpose is intimately linked with the high CC as almost all the complexly comes from if statements different eventualities of how the input strings are formatted. And the complexity of this function is actually even higher as almost all predicates in these if statements are functions making further checks on the string format.
    4. This function does not have any try catch statements that Lizard could take into account.
    5. No, the documentation of the function is quite minimal. While there are comments explaining some of the process, the actual overall purpose and outcome is not explained. And this combined with the fact that both return statements are further function calls makes the function hard to get an understanding of.

  
1. What are your results for five complex functions?
   * Did all methods (tools vs. manual count) get the same result?
   * Are the results clear?
2. Are the functions just complex, or also long?
3. What is the purpose of the functions?
4. Are exceptions taken into account in the given measurements?
5. Is the documentation clear w.r.t. all the possible outcomes?

## Refactoring

Plan for refactoring complex code:

Estimated impact of refactoring (lower CC, but other drawbacks?).

Carried out refactoring (optional, P+):

git diff ...

## Coverage

### Tools

Document your experience in using a "new"/different coverage tool.

How well was the tool documented? Was it possible/easy/difficult to
integrate it with your build environment?

### Your own coverage tool

Show a patch (or link to a branch) that shows the instrumented code to
gather coverage measurements.

The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:

git diff ...

What kinds of constructs does your tool support, and how accurate is
its output?

### Evaluation

#### Jonatan Tuvstedt
Coverage result before new tests: [true, true, false, true, true, false, true, true, false, true, true, true, true, false, true, true, true, true] = 14/18 branches taken ~ 78% coverage
1. As the instrumentation is done manually it should not be a problem to add probes to ternary operators. I also think it should be possible to manually add a probes for exceptions.
2. It is hugely limited as it completely relies on manual labour to check coverage and any changes to the code would result in having to redo some or all of the probes. Finally another limitation is that the changes made to introduce the probes causes some unrelated tests to fail.
3. 

1. How detailed is your coverage measurement?

2. What are the limitations of your own tool?

3. Are the results of your tool consistent with existing coverage tools?

## Coverage improvement

Show the comments that describe the requirements for the coverage.

Report of old coverage: [link]

Report of new coverage: [link]

Test cases added:

git diff ...

Number of test cases added: two per team member (P) or at least four (P+).

## Self-assessment: Way of working

Current state according to the Essence standard: ...

Was the self-assessment unanimous? Any doubts about certain items?

How have you improved so far?

Where is potential for improvement?

## Overall experience

What are your main take-aways from this project? What did you learn?

Is there something special you want to mention here?
