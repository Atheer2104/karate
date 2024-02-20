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

#### done by: Jonatan Tuvstedt
12. ApacheHttpClient::invoke@264-347@./src/main/java/com/intuit/karate/http/ApacheHttpClient.java
13. ScenarioEngine::match@1788-1843@./src/main/java/com/intuit/karate/core/ScenarioEngine.java
  
1. What are your results for five complex functions?
   * Did all methods (tools vs. manual count) get the same result?
   * Are the results clear?
2. Are the functions just complex, or also long?
3. What is the purpose of the functions?
4. Are exceptions taken into account in the given measurements?
5. Is the documentation clear w.r.t. all the possible outcomes?

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


#### Carried out refactoring (optional, P+)
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

2. **What are the limitations of your tool?  How would the instrumentation change if you modify the program?**

My tool again is manual instrumentation so it's very limited because nothing happens automatically everything has to be done manually, so if the method would be
changed then I would have to make sure that the manual instrumentation is correct and upto date, which is very bad since it has zero flexibility.

3. **if you have an automated tool, are your results consistent with the ones produced by existing tool(s)?**

I have used JaCoCo and it does not show a branch coverage percentage for a specific function but in the IDE it highlight that certain paths has been executed which
is inline with what the manual instrumentation reported which was roughly 63% were out of 27 total branch paths, 17 was reached.

## Coverage improvement

Show the comments that describe the requirements for the coverage.

### Report of old coverage

#### Atheer Salim [link](https://github.com/DD2480-group8-VT24/karate/blob/atheer_adhoc/karate-core/atheer_branchCoverage_result)

### Report of new coverage

#### Atheer Salim [link](https://github.com/DD2480-group8-VT24/karate/blob/atheer_adhoc_increased_coverage/karate-core/atheer_branchCoverage_result)

### Test cases added

#### Atheer Salim: ```git diff atheer_adhoc_increased_coverage..atheer_adhoc```

### Number of test cases added: two per team member (P) or at least four (P+).

Atheer Salim: 2 test cases added

## Self-assessment: Way of working

Current state according to the Essence standard: ...

Was the self-assessment unanimous? Any doubts about certain items?

How have you improved so far?

Where is potential for improvement?

## Overall experience

What are your main take-aways from this project? What did you learn?

Is there something special you want to mention here?
