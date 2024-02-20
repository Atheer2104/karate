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
   1.  Manual CC count for this function: 
   - `if`: 18
   - `while`: 2
   - `||`: 2
   - `return`: 0
   - `throw`: 0. 
   - **Total: 24** (Also counted by Melissa)

   Using Lizard the result was 25 which is almist the same but one higher than my manual count. This might be a difference in how the tool counts compared how I did it manually. 

   2. The function has an NLOC of 74. This means that there is about 1 CC per 3 NLOC. This feels like a high CC per line which means the function is not very long but has a high CC.

   3. There is no function level documentation for this functions which makes it harder to dicern the purspose. It seems to take an object of a list, map or string and recursevily and convert it to a string in Json format.

   4. There is no try or catch blocks in this function.

   5. There is no documentation about this function other than a small comment on a single line about a special case. This makes it hard to understand how the function runs and what it does. 


#### done by: Atheer Salim

**Selected Funtion:** ScenarioEngine::evalKarateExpression@2160-2240@./src/main/java/com/intuit/karate/core/ScenarioEngine.java

1. The manual count of CC for this function was computed to be 13, which is much less what the lizard tool computed it to be, which was 22.
   Here the formula that was used was the once showcased in lecture 4, more on complexity. I think the computation of the cyclomatic complexity
   is straight forward, but depending on the function and its structure, it can be harder to compute it via pen and paper since it's error-prone.
   I also don't exactly know how the lizard tool got 22, that is unclear.  
   My computation is as follows  
   Decision points:
   ||: 3
   if: 17
   &&: 1,
   Exit points:
   return: 10
   throw: 0  
   CC = Decision Points - Exit Points + 2 = 13

2. This function has quite a high NLOC of 71, but I think the main reason this function has a high CC is because that it has many if/else-if/else
   statements.

3. The purpose of this function is to evaluate any possible karate expression, it has to do a lot of things such as parsing the expression
   and evaluating them, it can evaluate Js, Json and XML. This function lies in at the heart of this testing library because the testing code that the
   user writes is using the custom karate language. Since this function performs the actual evaluation of these expressions,
   it makes sense that this function has high CC,

4. This function does not throw anything; therefore, it won't affect when lizard is used to compute the CC for this function.

5. No, there is no documentation for this function at all which actually makes it hard for potential contributors to understand what it actually
   does. The function has a good name, but that is still not enough one has to perform manually code review to understand the different parts of the function.

#### done by: Jonatan Tuvstedt ScenarioEngine.match()

**Selected Funtion:** ScenarioEngine::match@1788-1843@./src/main/java/com/intuit/karate/core/ScenarioEngine.java

1. Doing a manual CC count for this function we both got a CC of 20, compared to a CC of 21 from Lizard. As the formula used (the one in the lecture slides) is unambiguous I assume that Lizard uses a slightly different formula, but I didn't manage to find how the count their CC.

2. The function is not very long, having a NLOC of only 46. In fact most of the CC comes from just 2 very long if statements with 6 respectively 7 predicates.

3. As there is no function level documentation for this function discerning the purpose is not super easy. The general purpose seems to be a step in the process of matching a string of a specific type to a expected result. This purpose is intimately linked with the high CC as almost all the complexly comes from if statements different eventualities of how the input strings are formatted. And the complexity of this function is actually even higher as almost all predicates in these if statements are functions making further checks on the string format.

4. This function does not have any try catch statements that Lizard could take into account.

5. No, the documentation of the function is quite minimal. While there are comments explaining some of the process, the actual overall purpose and outcome is not explained. And this combined with the fact that both return statements are further function calls makes the function hard to get an understanding of.

6. What are your results for five complex functions?
   - Did all methods (tools vs. manual count) get the same result?
   - Are the results clear?
7. Are the functions just complex, or also long?
8. What is the purpose of the functions?
9. Are exceptions taken into account in the given measurements?
10. Is the documentation clear w.r.t. all the possible outcomes?

## Refactoring

### Plan for refactoring complex code

Estimated impact of refactoring (lower CC, but other drawbacks?).

#### Atheer Salim - src/main/java/com.intuit.karate/core/ScenarioEngine.java - (function) evalKarateExpression

I would say the complexity of this function is necessary as the way it is currently, because as specified this function has to parse and be able to evaluate
any karate expression. Because of this, the function requires many if/else-if/else statements to handle the respective parts. I think the function is actually
well written in the sense that whenever possible it does the necessary parsing and directly calls the respective target function. You could if you want split the
function into smaller parts, but I don't think that would be a good idea since in order to understand this function one would have to jump around the code, and
it would not bring any benefits. I'd rather have the function be longer and have everything in the same place, it makes it easier to actually understand what is
happening.

#### Jonatan Tuvstedt - ScenarioEngine.match

Most of the cyclomatic complexity in `ScenarioEngine.match(Match.Type matchType, String expression, String path, String rhs)` comes from handling different eventualities for the format of the input string expression. One way to slightly reduce the complexity of the function is to move the initial string formatting to its own function, ie moving the code between lines 1789 and 1806 to their own function taking in `expression` and returning `name` and `path`. This would reduce the CC of the function by 5 down to 15, a reduction of 25%. Further reductions might be possible but will also likely make the function much harder to parse as a majority of the complexity left comes from just 2 long if statements. A small further improvement would be to merge the 3 predicates on line 1820 to a single function call `parenthesiseWrapped(name)`, reducing the CC by a further 2.

#### Carried out refactoring (optional, P+)

git diff ...

## Coverage

### Tools

#### Document your experience in using a "new"/different coverage tool.

The third-party coverage tool that we used was JaCoCo, the experience was good when using the tool, it was easy to set up
and get started using it, this was mostly because of IntelliJ IDEA IDE for Java since you only change a few settings
highlighting that you want to use JaCoCo and that you want to measure the branch coverage. Then can run the test with coverage
enabled and it will show the results in a separate window. JaCoCo showed the branch coverage % but only for the whole .java file,
one couldn't see the % for a specific function. However in the IDE when viewing the actual code it marked code lines that have been
executed with green and those that haven't been executed with red which was good and very helpful.

#### How well was the tool documented? Was it possible/easy/difficult to integrate it with your build environment?

JaCoCo has good documentation on how to use it as a tool whilst also highlighting the features that it supports.
As mentioned earlier the tool was easy to set up and use in the build environment and again this was because of IntelliJ
it made it easy to use the tool, and run the test with coverage instrumentation enabled.

### Your own coverage tool

Show a patch (or link to a branch) that shows the instrumented code to
gather coverage measurements.

The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:

git diff ...

What kinds of constructs does your tool support, and how accurate is
its output?

#### Atheer Salim [link](https://github.com/DD2480-group8-VT24/karate/commit/f88f3dc146acbba0bcecdde040ce79108643f129)

### Evaluation

1. How detailed is your coverage measurement?

2. What are the limitations of your own tool?

3. Are the results of your tool consistent with existing coverage tools?

#### Atheer Salim 3.5.1 DIY

1. **What is the quality of your own coverage measurement? Does it take into account ternary operators (condition ? yes : no)
   and exceptions, if available in your language?**

The quality of the coverage measurement is accurate and correct since we are doing manual instrumentation we just to check and make sure that each
branch for a branch when it's evaluated to true and false we take note of that and mark it. Since the function that I'm analyzing does not have any ternary
operator it does not consider them, but if the function would have a ternary operator then, it would probably have to be rewritten to a normal if/else
because evaluates one expression, which is cumbersome and error-prone

2. **What are the limitations of your tool? How would the instrumentation change if you modify the program?**

My tool again is manual instrumentation so it's very limited because nothing happens automatically everything has to be done manually, so if the method would be
changed then I would have to make sure that the manual instrumentation is correct and upto date, which is very bad since it has zero flexibility.

3. **if you have an automated tool, are your results consistent with the ones produced by existing tool(s)?**

I have used JaCoCo and it does not show a branch coverage percentage for a specific function but in the IDE it highlight that certain paths has been executed which
is inline with what the manual instrumentation reported which was roughly 63% were out of 27 total branch paths, 17 was reached.

#### Jonatan Tuvstedt

Coverage result before new tests: [true, true, false, true, true, false, true, true, false, true, true, true, true, false, true, true, true, true] = 14/18 branches taken ~ 78% coverage.

[Patch](https://github.com/DD2480-group8-VT24/karate/commit/e14ffe8ecfb29a5f8c3a63268f318583dbc8e37e) for Instrumented code or just run

`git diff e49ece113f688cb57d90f46a2ad47149fb67e59f e14ffe8ecfb29a5f8c3a63268f318583dbc8e37e`

1. As the instrumentation is done manually it should not be a problem to add probes to ternary operators. I also think it should be possible to manually add a probes for exceptions. Quality wise I would say that it is optimal as it takes all possible branches into account (unless I have messed up).
2. It is hugely limited as it completely relies on manual labour to check coverage and any changes to the code would result in having to redo some or all of the probes. Finally another limitation is that the changes made to introduce the probes causes some unrelated tests to fail.
3. When doing a manual inspection of the results by JaCoCo it is identical to the one found by my DIY solution.

#### Marcus Odin 3.5.1 DIY

10/34 branches were taken before the new tests were implemented. [link](https://github.com/karatelabs/karate/compare/master...DD2480-group8-VT24:karate:marodi-adhoc#diff-ed7b53f024c5cc83536c8b757c24f020cc382ee59079a462fc7b3d62933682bb)
1. There are no ternary operators in the function but it should be able to be taken into account if that was the case. The quality should be good since it is done manually.

2. If you modify the program or add or remove branches for example then you would have to manually remake everything that was affected. It is also important to not accidentally affect other parts of the program just to check the coverage.

## Coverage improvement

Show the comments that describe the requirements for the coverage.

### Report of old coverage

#### Atheer Salim [link](https://github.com/DD2480-group8-VT24/karate/blob/atheer_adhoc/karate-core/atheer_branchCoverage_result)

#### Jonatan Tuvstedt

[true, true, false, true, true, false, true, true, false, true, true, true, true, false, true, true, true, true] = 14/18 ~ 78%

#### Marcus Odin [link](https://github.com/DD2480-group8-VT24/karate/blob/marodi-adhoc/karate-core/branchCoverage_result)

### Report of new coverage

#### Atheer Salim [link](https://github.com/DD2480-group8-VT24/karate/blob/atheer_adhoc_increased_coverage/karate-core/atheer_branchCoverage_result)

#### Jonatan Tuvstedt

[true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, true, true] = 17/18 ~ 94%

#### Marcus Odin [link](https://github.com/DD2480-group8-VT24/karate/blob/marodi_adhoc_increased_coverage/karate-core/branchCoverage_result)

### Test cases added

#### Atheer Salim: `git diff atheer_adhoc_increased_coverage..atheer_adhoc`

#### Jonatan Tuvstedt:

**Requirements:** `git diff 3398d67d6bb32edb8fe7e2abd0c77ad68d8f848b 3de6a18908d159ca2d2b96588a09522bd6b2a4af`
**Tests:** `git diff c0277857d337ccff679f362b0f1bfc626d687037 3398d67d6bb32edb8fe7e2abd0c77ad68d8f848b`

The branches that were untested in ScenarioEngine.match are as follows:

1. The removing of a leading $ in name
2. Extra checks when name is surrounded by parenthesis
3. The input expression being header
4. The case where the expression neither isXmlPathFunction, isDollarPrefixed, isJsonPath nor isXmlPath.

My tests addresses 1 (`testRemovingLeadingDollarWorks()`), 2 and 4 (`testRemovingSurroundingParenthesisWorks()`) bringing the branch coverage up to ~ 94% for ScenarioEngine.match()

#### Marcus Odin [link](https://github.com/karatelabs/karate/commit/9d18488455e4583a700953185835a5133fb008ca#diff-c01545e0cfeffbbb3776287cb80f9e7b3970bb8d2b07c96a90ee68a756661308)

The tests I added were `testNull()` and `testList()`. I added these tests since the function I was working on had 3 if/else statements at the highest level but only one of them were accesed so my tests gets some coverage on the other 2 aswell.

### Number of test cases added: two per team member (P) or at least four (P+).

Atheer Salim: 2 test cases added

Jonatan Tuvstedt: 2 test cases added

Marcus Odin: 2 test cases added

## Self-assessment: Way of working

Current state according to the Essence standard: ...

Was the self-assessment unanimous? Any doubts about certain items?

How have you improved so far?

Where is potential for improvement?

## Overall experience

### What are your main take-aways from this project? What did you learn?

One key takeaway was that doing the manual instrumentation for branch coverage was a good exercise for us since it
showed us in practice how it works and how one could add the instrumentation. In the beginning, it sounded more difficult and scary
than it actually was but again our instrumentation is limited since it's manual and is not performed automatically which is more challenging to implement.

Another key takeaway for us was that it can be hard to start working on a new open-source project since there is a lot of time that goes
to understand the architecture of the project, how everything is split up, where to find things, etc... Additionally for this project
it was a bit harder to improve and increase the branch coverage mostly because the functions did not have any documentation, which
made it harder to grasp and understand what the functions do.

One thing that we learned is that it can be hard to get started working on an open-source project since it all depends on the available
information. There can be problems with building and running the project but luckily for us this project had great documentation on how to get started and followed standard conventions which made it easy to run properly.

We furthermore saw a good example of why it's important to have tests and how important they are for a project.

### Is there something special you want to mention here?
Nothing else to add here
