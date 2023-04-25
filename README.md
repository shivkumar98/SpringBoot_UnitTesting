<link href="style.css" rel="stylesheet"></link>

# 🟪 Spring Boot Unit Testing with JUnit, Mockito and MockMvc

* These are my notes while working through Chad Darby's Udemy course on unit testing with the spring framework! 😊

* I started this course on 15/04/2023 📅

* You can find the course [here](https://www.udemy.com/course/spring-boot-unit-testing)

<br>
<hr>

# 🟪 Section 1 - Introduction

## 🟦 1.1 Course Introduction

* Spring Boot supports unit test and integration testing vvia JUnit and Mockito

* This course will show you how to take advantage of testing support in Spring

* The course road map consists of:

    1) JUnit Review

    2) Spriong Boot Mocks with Mockito

    3) Spring Boot Testing Spring MVC Web App

    4) Spring Boot Testing Spring REST APIs

## 🟦 1.2 Setting up Dev environment

* Dev environment will be intelliJ for Maven support, and MySQL Database Server and Workbench

* I downloaded and installed the MySQL Developer Default setup!

* The source code for this course can be found [here](https://github.com/darbyluv2code/spring-boot-unit-testing)

<br>
<hr>

# 🟪 Section 2 - JUnit Review

## 🟦 2.1 Unit Testing Overview

### 🟥 How Do We Typically Write Tests?

* Typically we write small programs which test our code and examine the results. The test case is not reproduceable and we are unable to automate the tests


### 🟥 What is Unit Testing?

* 🎃 Unit Testing is testing individual unit of code. We have a fixed input and expected known output 🎃

* Suppose we had a calculator program with an `add(int x, int y)` method

    * We would write tests such as:

    1) Does it work with positive?

    2) Does it work with negatives?

    3) Does it work with zero?

#### 🟧 Benefits of Unit Testing:

* Automated tests✔️ Better Code Design✔️ Fewer bugs and reliability✔️

* We can be confident extending/modifying our code ✔️

* Great for automated build processes ✔️

### 🟥 What is Integration Testing?

* 🎃 Integeration Testing is testing multiple components together as part of a test plan 🎃

* We can determine if units of code can work together ✔️

* We can use mocks and stubbing for integration tests ✔️


### 🟥 Unit Testing Frameworks

1) JUnit:

    * Supports creating test cases
    * Automation of test cases
    * Utilities for setup and tearing down tests

2) Mockito:

    * Used for creating mocks and stubs
    * Minimise dependencies (such as databases)

<br>

## 🟦 2.2 Developing JUnit Tests - Overview + Demo

### 🟥 Introduction

* We shall look at some simple examples for unit testing 🎯

* We will look at fundamentals like defining and running tests, and using assertions 🎯

* Discuss Test-Driven Development 🎯

### 🟥 Development Process:

* We shall create a `DemoUtil` class with a method: `add(int a, int b)`, and write unit tests for it. The development process is:

1) Create a Maven project with JUnit dependency 🎯

2) Create test package, write unit test and run test 🎯

* The Maven dependency is:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
</dependency>
```

* The scope is saying that this is for **testing** only, not for normal execution of the code

* We shall setup a `test` package and follow the convention of matching the package structure of the `main` package

* Unit tests have the following structure:

    1) Setup
    2) Execute
    3) Assert

* We use the `@Test` annotation above the test method.

* We setup by instantiating thr `DemoUtil`, execute the `add()` method and assert the expected result

* We then run the test in the IDE

### 🟥 JUnit Assertions

* `JUnit` has a collection of assertions which are defined in `org.junit.jupiter.api.Assertions`

* The assertion methods include:

1) `assertEquals(expected, actual, optionalReturnMessage)`

2) `assertNotEquals(unexpected, actual, optionalReturnMessage)`

2) `assertNull(actual, optionalReturnMessage)`

4) `assertNotNull(actual, optionalReturnMessage)`


### 🟥 Static Import

* We can import the methods from `Assertions` class statically, so we can call the methods without referencing the class

```java
import static org.junit.jupiter.api.Assertions.*; // imports ALL methods
```

<hr>

###  🖥️⌨️🖱️ Project Setup 🖱️⌨️🖥️

* I download the code repository from [https://github.com/darbyluv2code/spring-boot-unit-testing](https://github.com/darbyluv2code/spring-boot-unit-testing)

* I copy `1.00-starting-project` into [this](/Demos/demo-01-project_setup/) folder

* I open up the [POM](/Demos/demo-1/1.00-starting-project/pom.xml) in intelliJ and see this:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.luv2code</groupId>
    <artifactId>junitdemo</artifactId>
    <version>1.0</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

</project>
```

* I add the JUnit dependency using intelliJ's generate button:

```xml
   <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.9.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

<br>

## 🟦 2.3 JUnit Assertions

* I add a new package into the `test` package and the `DemoUtilTest` class:

![](screenshots/2023-04-15-12-39-51.png)

* I write the test:

```java
    @Test
    void testEqualsAndNotEquals(){
        // setup:
        DemoUtils demoUtils = new DemoUtils();
        // using assertEquals and NotEquals:
        assertEquals(6, demoUtils.add(2,4), "2+4 should equal 6");
        assertNotEquals(6, demoUtils.add(1,9), "1+9 should not be 6");
    }
```

* I run the test and it passes as expected:

![](screenshots/2023-04-15-12-54-48.png)

* I create a method for checking Nulls:

```java
    @Test
    void testNullAndNotNull() {
        DemoUtils demoUtils = new DemoUtils();

        String str1 = null; String str2 = "luv2code";

        assertNull(demoUtils.checkNull(str1));
        assertNotNull(demoUtils.checkNull(str2));
    }
```

* The test passes as expected!!!


<br>

## 🟦 2.4 Test Lifecycle Methods

### 🟥 Lifecycle Methods

* Some common operations is setting up the environment and cleaning things after tests finish.

* JUnit provides Lifecycle Method Annotations, including:

1) `@BeforeEach` - Method is executed before EACH test method

2) `@AfterEach` - Method to be executrer after EACH test method

3) `@BeforeAll` - Only executed ONCE before all test methods. MUST be static

4) `@AfterAll` - Only executed ONCE after all test methods. MUST be static

### 🟥 Execution Sequence

![](screenshots/2023-04-15-13-08-24.png)

<hr>

###  🖥️⌨️🖱️ Code Demo 🖱️⌨️🖥️

* I remove the instantiation of the `DemoUtils` class in each test method and setup a class field.

* I use the `@BeforeEach` annotation to write a method which instantiates `DemoUtils`, and write a method with `@AfterEach` which just prints a diagnostic message:

```java
public class DemoUtilsTest {

    // Creating a DemoUtil field
    DemoUtils demoUtils;

    @BeforeEach
    void setUpBefore(){
        demoUtils = new DemoUtils();
        System.out.println("BeforeEach executes before each test method");
    }

    @Test
    void testEqualsAndNotEquals(){
        System.out.println("Running test: testEqualsAndNotEquals");
        // using assertEquals and NotEquals:
        assertEquals(6, demoUtils.add(2,4), "2+4 should equal 6");
        assertNotEquals(6, demoUtils.add(1,9), "1+9 should not be 6");
    }

    @Test
    void testNullAndNotNull() {
        System.out.println("Running test: testNullAndNotNull");
        String str1 = null; String str2 = "luv2code";
        assertNull(demoUtils.checkNull(str1));
        assertNotNull(demoUtils.checkNull(str2));
    }

    @AfterEach
    void tearDownAfterEach(){
        System.out.println("Running @AfterEach");
    }
}
```

* Running the test class, shows the following in console:

```console
BeforeEach executes before each test method
Running test: testNullAndNotNull
Running @AfterEach
BeforeEach executes before each test method
Running test: testEqualsAndNotEquals
Running @AfterEach
```


<br>

## 🟦 2.5 Defining Custom Display Names

### 🟥 Custom Display Names

* The name of the methods show up in the test results

* We can use custom display names to provided descriptive names for each tests

* 🎃 We use `@DisplayName` to make a custom display name 🎃

* We can add this annotation below the `@Test` annotation:

```java
    @Test
    @DisplayName("Null and not null")
    void testNullAndNotNull(){
        // test implementation ....
    }
```

### 🟥 Display Name Generators

* JUnit can create display names for you:

1) `Simple` - removes the () for you

2) `ReplaceUnderscores` - this replaces _ with spaces

3) `IndicativeSentences` - creates name based on test class name and method name

* We can use generators WITHOUT using `@DisplayName` for every method. We can define one main annotation at class level using `@DisplayNameGeneration` (⚠️can not use it on methods!)

#### 🟧 Simple Generator

```java
@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
class DemoUtilsTest {
    //....
}
```

#### 🟧 Replace Underscores Generator

```java
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DemoUtilsTest {
    // ...
}
```

#### 🟧 Indicatibe Sentences Generatro

```java
@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
class DemoUtilsTest {
    // ...
}
```

<hr>

###  🖥️⌨️🖱️ Code Demo 🖱️⌨️🖥️

* I start working on the demo [here](/Demos/demo-02-name_generators/)

* Using the following annotation:

```java
@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
public class DemoUtilsTest {
    // ....
}
```

* This displays:

![](screenshots/2023-04-15-15-03-13.png)

* I can override the generation at class level:

```java
    @Test
    @DisplayName("This tests shows equality")
    void testEqualsAndNotEquals(){
        System.out.println("Running test: testEqualsAndNotEquals");

        // using assertEquals and NotEquals:
        assertEquals(6, demoUtils.add(2,4), "2+4 should equal 6");
        assertNotEquals(6, demoUtils.add(1,9), "1+9 should not be 6");
    }
```

* This displays:

![](screenshots/2023-04-15-15-05-11.png)

<br>

## 🟦 2.6 JUnit Assertions - Same/NotSame and True/False

### 🟥 Assert Same and Not Same

* `assertSame()` - asserts that items refer to same object

* `assertNotSame()` - asserts that items do NOT refer to same object


```java
    @Test
    void testSameAndNotSame() {
        String str = "123";
        assertSame(str, "123"); // PASSES!
        assertNotSame(str, new DemoUtilsTest()); // PASSES!
    }
```

### 🟥 Asserts True and False

* `assertTrue` - asserts condition is true

* `assertFalse` - asserts condition is false

```java
    @Test
    void testTrueAndFalse(){
        assertTrue(1==1, "one must equal one!"); // PASSES!
        assertFalse(1>1, "one cannot be greater than one!"); // PASSES
    }
```

<br>

## 🟦 2.7 Arrays, Iterable and Lines

### 🟥 assertArrayEquals() Method

* The `assertArrayEquals()` method asserts two object arrays are deeply equal (content is the same)

```java
    @Test
    void arrayIsDeeplyEqual(){
        String[] firstThreeLetters = {"A", "B", "C"};
        assertArrayEquals(firstThreeLetters, new String[] {"A", "B", "C"}); // PASSES!
        assertArrayEquals(firstThreeLetters, new String[] {"A", "C", "B"}); // FAILS!!
    }
```

### 🟥 assertIterableEquals() Method

* The `assertIterableEquals()` method asserts iterable objects are deeply equal! Classes which implement Iterable include LinkedList, ArrayList, HashSet, ...

```java
    @Test
    void iterableIsDeeplyEqual(){
        List<String> list = List.of("Shiv", "Likes", "Java");
        assertIterableEquals(list, List.of("Shiv","Likes", "Java")); // PASSES!
        assertIterableEquals(list, List.of("Shiv","Likes")); // FAILS!
    }
```

### 🟥 assertLinesEquals() Method

* The `assertLinesMatch` asserts that list of Strings match!

```java
    @Test
    void LinesShouldMatch(){
        List<String> list = List.of("Shiv", "Likes", "Java");
        assertLinesMatch(list, List.of("Shiv","Likes","Java")); // PASSES!!
        assertLinesMatch(list, List.of("Shiv","Java","Likes")); // FAILS!!
    }
```

<br>

## 🟦 2.8 Assertion for Throws and Timeouts

### 🟥 Assertion For Throws

* `assertThrows(Class expectedType, Executable executable)` - asserts if an exception is throw in a given lambda

* `assertDoesNotThrow(Executable executable)` - asserts that ANY exception is not thrown


* The `DemoUtils` class has the following method:

```java
public class DemoUtils {
    public String throwException(int a) throws Exception {
        if (a<0)
            throw new Exception("Value should be greater than or equal to 0");
        return "Value is greater than or equal to 0";
    }
}
```

* We can write a test:


```java
    @Test
    void testThrowsAndDoesNotThrow() {
        assertThrows(Exception.class,
                     ()->{ demoUtils.throwException(-1); },
                   "An exception should be thrown"); // PASSES!!

        assertThrows(Exception.class,
                ()-> {throw new RuntimeException();},
                "Should throw Exception class"); // PASSES

        assertThrows(RuntimeException.class,
                ()-> { throw new Exception();},
                "Should throw RuntimeException class"); // FAILS
        

        assertDoesNotThrow(()-> {demoUtils.throwException(1);} ); // PASSES!!

    }
```

### 🟥 Assertion for Timeouts

* `assertTimeOut(Duration stimeout, Executable executable)` - asserts that execution completes before time out is exeeded

* `assertTimeoutPreemptively(Duration timeout, Executable executable)` - again, asserts execution completes before timeout is exceeded, but also aborts execution after timeout

* The `DemoUtils` class has the following method:

```java
public class DemoUtils {
    public void checkTimeOut() throws InterruptedException {
        System.out.println("I am going to sleep");
        Thread.sleep(2000);
        System.out.println("Sleeping over");
    }
}
```

* I write the following test:

```java
    @Test
    void testTimeOutAndTimeOutPremptively(){
        assertTimeout(Duration.ofSeconds(3), ()-> {demoUtils.checkTimeout(); } ); // PASSES!
        /* CONSOLE:
            I am going to sleep
            Sleeping over
         */

        assertTimeout(Duration.ofSeconds(2), ()-> {demoUtils.checkTimeout(); } ); // FAILS!
        /* CONSOLE:
            I am going to sleep
            Sleeping over
            org.opentest4j.AssertionFailedError: execution exceeded timeout of 2000 ms by 6 ms
         */

        assertTimeoutPreemptively(Duration.ofSeconds(1), ()->{demoUtils.checkTimeout();} ); // FAILS!
        /* CONSOLE
            I am going to sleep
            org.opentest4j.AssertionFailedError: execution timed out after 1000 ms
         */

    }
```


<br>

## 🟦 2.9 Ordering JUnit Tests

### 🟥 Introduction

* We can visually change the order of the test report in the IDE. Without any ordering the test results looks like:

![](screenshots/2023-04-16-09-19-56.png)

* By default, the tests are deterministic but sorted in a pseudo random way intentionally.

* ⚠️In general, ordering should not actually be part of the tests. Unit testing is meant to be independent!⚠️

### 🟥 How to Order Tests

* We can use the `@TestMethodOrder()` annotation to specify ordering.

* We have 4 ways of ordering:

1) `MethodOrderer.DisplayName` sorts test methods alphanumerically using display names

2) `MethodOrderer.MethodName` sorts tests methods alphanumerically using method names

3) `MethodOrderer.Random` sorts test methods randomly!

4) `MethodOrderer.OrderAnnotation` sorts method based on `@Order` annotation

### 🟥 Using @Order

* The `@Order` annotation lets us specify priority of a test using an `int`

* E.g., if we have the following ordering:

```java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
public class DemoUtilsTest {

    DemoUtils demoUtils= new DemoUtils();

    @Order(1)
    @Test
    @DisplayName("This tests shows equality")
    void testEqualsAndNotEquals(){
        // ....
    }

    @Order(-33)
    @Test
    void testNullAndNotNull() {
        // ....
    }

    // other tests with no annotation
}
```

* Then the test results look like:

![](screenshots/2023-04-16-09-37-20.png)

* 💡 The rest of the tests follow the deterministic algorithmic ordering 💡

* 💡 If there are duplicate entries, then it will be ordered using the algorithm forementioned 💡

<br>

## 🟦 2.10 Unit Testing Code Coverage with IntelliJ

### 🟥 Code Coverage

* 💡 Code coverage measures how many of the methods and lines are called via your test 💡

* 🎃 We should strive for 70-80% code coverage, 100% code coverage can be achieved but would not be a meaningful metric 🎃

* IntelliJ has built in support for code coverage! We can also generate reports using Maven in command line

* In order to see the report in intelliJ, you right-click and go to:

    ![](screenshots/2023-04-16-09-55-05.png)

* We can see the code coverage in the `DemoUtils` class:

    ![](screenshots/2023-04-16-10-37-48.png)

    * We can see the red lines means not covered and green means code is covered

* We can generate a report by clicking on:

    ![](screenshots/2023-04-16-10-38-55.png)

* We then get a promp for where to store the report:

    ![](screenshots/2023-04-16-10-39-33.png)

* If we open up the report, and navigate to the class:

    <img src="screenshots/2023-04-16-10-40-11.png" width="500px">

    * We can see the `muliply()` method has not been covered, so we could write a test for it

* If we want to generate a test report, we can click on:

    <img src="screenshots/2023-04-16-10-41-44.png" width="500px">

* Opening the report shows:

    <img src="screenshots/2023-04-16-10-42-28.png" width="500px">


<br>

## 🟦 2.11 Unit Testing Code Coverage with Maven

### 🟥 Maven

* We can also do Code coverage using Maven in the command line!

* You must have Maven installed (download [here](https://maven.apache.org/download.cgi)) - make sure to download the binary!

### 🟥 Configuring Maven

* 💡 By default Maven will NOT find JUnit 5 tests, we must configure it! We can add Maven Surefire plugin version 2.22.0 or higher. I add the following to the POM:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifaceId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M5</version>
        </plugin>
    </plugins>
</build>
```

### 🟥 Running Unit Tests

* I navigated to my projects source folder ([here](/Demos/demo-05-testing_with_maven/1.00-starting-project/))

* I ran `mvn clean test` and the test ran in the command prompt!:

    ![](screenshots/2023-04-16-11-20-58.png)

### 🟥 Generating Unit Test Reports

* We can use the SureFire plugin to generate unit test reports! We need to add some information to the `<plugin>` tag:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.0.0-M5</version>
    <executions>
        <execution>
            <goals>
                <goal>test</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

* This saying, during "test" phase execute the "report" goal

* Re-running `mvn clean test`

* The report gets generated here:

![](screenshots/2023-04-16-11-34-48.png)

* By default, Surefire will not generate reports if test fails. We can add a configuration to the plugin in the POM xml:

```xml
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.0.0-M5</version>

    </plugin>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-report-plugin</artifactId>
        <version>3.0.0-M5</version>
        <executions>
            <execution>
                <phase>test</phase>
                <goals>
                    <goal>report</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
</plugins>
```

* If we run `mvn clean test` then we get reports with no stylings:

    ![](screenshots/2023-04-16-11-50-01.png)

* We need to execute another command, to allow for the css and images: `mvn site -DgenerateRerports=false`

* Our report is now styled:

    ![](screenshots/2023-04-16-11-52-34.png)

* We need to enable the report to be generated WHEN tests FAIL! We need to add a configuration:

```xml
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.0.0-M5</version>
        <configuration>
            <testFailureIgnore>true</testFailureIgnore>
        </configuration>
    </plugin>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-report-plugin</artifactId>
        <version>3.0.0-M5</version>
        <executions>
            <execution>
                <phase>test</phase>
                <goals>
                    <goal>report</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
</plugins>
```

* I'll add a failing test and rerun `mvn clean install` and `mvn site -DgenerateReports=false`

```java
    @Test
    void purposlyFailingTest(){
        assertTrue(false);
    }
```

* And the report generates🎉:

    ![](screenshots/2023-04-16-11-59-03.png)

* We also need to configure the reports to show the Display names!!! I search the maven repo wiki and find: [this](https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit-platform.html#surefire-extensions-and-reports-configuration-for-displayname):

* I update the POM:

```xml
 <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.0.0-M5</version>
    <configuration>
        <testFailureIgnore>true</testFailureIgnore>

        <statelessTestsetReporter implementation="org.apache.maven.plugin.surefire.extensions.junit5.JUnit5Xml30StatelessReporter">
            <usePhrasedFileName>false</usePhrasedFileName>
            <usePhrasedTestSuiteClassName>true</usePhrasedTestSuiteClassName>
            <usePhrasedTestCaseClassName>true</usePhrasedTestCaseClassName>
            <usePhrasedTestCaseMethodName>true</usePhrasedTestCaseMethodName>
        </statelessTestsetReporter>
        <consoleOutputReporter implementation="org.apache.maven.plugin.surefire.extensions.junit5.JUnit5ConsoleOutputReporter">
            <disable>false</disable>
            <encoding>UTF-8</encoding>
            <usePhrasedFileName>false</usePhrasedFileName>
        </consoleOutputReporter>
        <statelessTestsetInfoReporter implementation="org.apache.maven.plugin.surefire.extensions.junit5.JUnit5StatelessTestsetInfoReporter">
            <disable>false</disable>
            <usePhrasedFileName>false</usePhrasedFileName>
            <usePhrasedClassNameInRunning>true</usePhrasedClassNameInRunning>
            <usePhrasedClassNameInTestCaseSummary>true</usePhrasedClassNameInTestCaseSummary>
        </statelessTestsetInfoReporter>
    </configuration>
</plugin>
```

* Now the report shows display names:

    ![](screenshots/2023-04-16-12-06-21.png)

### 🟥 Generating Code Coverage Reports

* We need another pluging:

```xml
 <plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.7</version>

    <executions>
        <execution>
            <id>jacoco-prepare</id>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>jacoco-report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

* We now run `mvn clean test` and the code generate report is created:


    ![](screenshots/2023-04-16-12-17-31.png)



<br>

## 🟦 2.12 Conditional Tests

### 🟥 Why?

* We may want to disable a given test, or have a test which only runs on specific versions of Java, or or run on a specific OS.

### 🟥 Annotations - Disabled and EnabledOnOS

* `@Disabled` - the test will not be executed but STILL show in the test reports.

* `@EnabledOnOs` - we can specify an OS, or multiple OS's in an array

```java
@Test
@Disabled("Do not run till issue fixed")
void someIgnoredTest() { /* ... */}

@Test
@EnabledOnOs(OS.MAC)
void testOnlyOnMAC() { /* ... */}

@Test
@EnabledOnOs({OS.WINDOWS, OS.MAC})
void testOnlyOnWindowsOrMAC() { /* ... */}
```

    ![](screenshots/2023-04-16-12-26-37.png)


### 🟥 Annotations - EnabledOnJre and EnabledOnJreRange

* We can disable/enable a test for a particular JRE or range:

```java
    @Test
    @EnabledOnJre(JRE.JAVA_17)
    @Order(10)
    void testOnlyForJRE17(){ } 

    @Test
    @Order(11)
    @EnabledForJreRange(min = JRE.JAVA_17)
    void testForAnythingAbove16() { } 

    @Test
    @Order(12)
    @DisabledForJreRange(max = JRE.JAVA_17)
    void testDisabledForAnyJREBelow18(){} // DOES NOT RUN
```

* The Result is:

    ![](screenshots/2023-04-16-12-37-27.png)

### 🟥 Annotations - EnabledIfSystemProperty and EnabledIfEnvironmentVariable

* We can enable/disable tests depending on system properties or environment variable

```java
    @Test
    @Order(13)
    @EnabledIfEnvironmentVariable(named = "MAVEN_HOME", matches = "C:\\\\apache-maven-3.6.3")
    void testShouldBeExecuted(){}

    @Test
    @Order(14)
    @EnabledIfEnvironmentVariable(named = "MAVEN_HOME", matches = "hello world")
    void testShouldNotBeExecuted() {}
```

* This displays:

    ![](screenshots/2023-04-16-12-49-37.png)

<br>
<hr>

# 🟪 Section 3 - Test Driven Development

## 🟦 3.1 What is Test Driven Development?

* In the past, development would have the following flow: Design ➡️ Code ➡️ Test.

* TDD has the following cycle: Test ➡️ Code ➡️ Refactor 🔁

* TDD is beneficial as it outlines the things to test and develop, identify edge cases, improve confidence in code base and the ability to refactor code!


## 🟦 3.2 Fizz Buzz

* Write a program which prints the first 100 FizzBuss numbers, if the number is

    - divisible by 3 then print Fizz
    - divisible by 5 then print Buzz
    - divisible by 3 and 5 then print FizzBuzz
    - number is not divisible by 3 or 5 then print the number

### 🟥 Coding Demo - FizzBuzz

* I created two tdd packages:

    ![](screenshots/2023-04-24-10-33-17.png)

* I define my test class:

```java
public class FizzBuzzTest {
    @Test
    void testForDivisibleByThree(){
        fail("fail");
    }
}
```

* I update my test:

```java
public class FizzBuzzTest {

    @Test
    void testForDivisibleByThree(){
        assertEquals("Fizz", FizzBuzz.compute(3), "3 should return Fizz");
    }
}
```

* I create my FizzBuzz class in the src package:


```java
public class FizzBuzz {
}
```

* I create the `compute()` method so the test passes!

```java
public class FizzBuzz {
    public static String compute(int i) {
        return null;
    }
}
```

* The test fails as expected:

    ![](screenshots/2023-04-24-10-40-39.png)

* I update the method, so that the test passes:

```java
    public static String compute(int i) {
        if (i % 3 == 0)
            return "Fizz";
        return null;
    }
```

* I create a new test:

```java
  @Test
    void testForDivisubleByFive(){
        assertEquals("Buzz", FizzBuzz.compute(5), "5 should return Buzz");
    }
```

* The test fails as expected! So I update the `compute()` method:

```java
 public static String compute(int i) {
        if (i % 3 == 0)
            return "Fizz";
        else if (i % 5 ==0) {
            return "Buzz";
        }
        return null;
    }
```

* All tests now pass! I now write a test for 15 which should return FizzBuzz:

```java
    @Test
    void testForDivisbleBy3And5(){
        assertEquals("FizzBuzz", FizzBuzz.compute(15), "15 should return FizzBuzz");
    }
```

* The test fails because Fizz returns! I update the `compute()` method:

```java
public static String compute(int i) {
    if (i % 3 == 0 && i % 5 ==0)
        return "FizzBuzz";
    else if (i % 3 == 0)
        return "Fizz";
    else if (i % 5 ==0) {
        return "Buzz";
    }
    return null;
}
```

* I add a new test for a number which is neither Fizz or Buzz:

```java
    @Test
    void testForNonFizzBuzzNumber(){
        assertEquals("1", FizzBuzz.compute(1), "1 should return 1");
    }
```

* It fails because it returns null! I update the `compute()` method again:

```java
public static String compute(int i) {
    if (i % 3 == 0 && i % 5 ==0)
        return "FizzBuzz";
    else if (i % 3 == 0)
        return "Fizz";
    else if (i % 5 ==0) {
        return "Buzz";
    }
    return ""+i;
}
```

* All tests pass!

### 🟥 Coding Demo - Refactoring FizzBuzz

* Since we have all our test passing, we can now refactor our method!

```java
public class FizzBuzz {
    public static String compute(int i){
        StringBuilder sb = new StringBuilder();
        if (i%3 == 0)
            sb.append("Fizz");
        if (i%5 == 0)
            sb.append("Buzz");
        if (sb.isEmpty())
            sb.append(i);
    }
}
```

* All tests still pass!

## 🟦 3.3 Parameterised Tests

### Introduction

*  Suppose we want to pass a collection of inputs and expected results for our FizzBuzz program

* One way could be to use an array:

```java
void testLoopOverArray(){
    String[][] data = { { "1", "1"},
                        { "2", "2"},
                        { "3", "Fizz"},
                        { "4", "4"},
                        { "5", "Buzz"},
                        { "6", "Fizz"},
                        { "7", "7"},
                      };
    
    for (int i=0; i<data.length; i++){
        String actual = data[i][0];
        String expected = data[i][1];
        assertEquals(expected, FizzBuzz.compute(Integer.parseInt(value)));
    }
}
```

* 😱 JUnit provides `@ParameterizedTest` which lets you run a test multiple times with different parameters. It does the looping for you in the background😮‍💨😮‍💨😮‍💨

* We can provide the parameters using an array of values✅, array of CSV Strings✅, CSV values from a file✅, an enum✅ or even a custom method✅

| Annotation     | Description      |
|----------------|------------------|
|`@ValueSource`     | Arrays of values |
|`@CsvFileSource`   | CSV values read from a file |
|`@CscSource`       | CSV values from a String  |
|`@EnumSource`      | Enum constant values        |
|`@MethodSource`    | Custom method for providing values |



### 🟥 Code Demo - Using CSV Files

```java
@ParameterizedTest
@CsvSource({"1,1",
            "2,2",
            "3,Fizz",
            "4,4",
            "5,Buzz",
            "6,Fizz",
            "15,FizzBuzz"})
void testUsingParameters(int number, String result){
    assertEquals(FizzBuzz.compute(number), result);
}
```

* I create a CSV file in a new directory:

    ![](screenshots/2023-04-24-13-31-53.png)

* I create a parameterised test which uses a CSV file source:

```java
@ParameterizedTest(name = "value={0}, expected={1}")
@CsvFileSource(resources = "/small-test-data.csv")
void test(int value, String expected){
    assertEquals(FizzBuzz.compute(value), expected);
}
```

* I specified a name on the @ParameterizedTest annotation to override the display name for each test!

* I copy `large-test-data.csv` and `medium-test-data.csv` into my resources:

    ![](screenshots/2023-04-24-13-43-35.png)

* I create a new method to test the large CSV:

```java
@ParameterizedTest(name="value:{0}, expected:{1}")
@CsvFileSource(resources = "/large-test-data.csv")
void testWithLargeData(int value, String expected){
    assertEquals(FizzBuzz.compute(value), expected);
}
```


## 🟦 3.4 Finishing the FizzBuzz Program

* The initial task was to write the numbers from 1 to 100 using FizzBuzz!

* I define a `MainApp` class in the tdd package in main

```java
public class MainApp {
    public static void main(String[] args) {
        for (int i=1;i<=100;i++){
            System.out.println(FizzBuzz.compute(i));
        }
    }
}
```

* The app works as expected!!!

<br>
<hr>

# 🟪 Section 4 - Spring Boot Unit Testing

## 🟦 4.1 Overview

### 🟥 Introduction

* You need the following for unit testing Spring Boot applications: Access to Spring Application Context✅, support for dependency injection✅, Retrieve data from Spring application.properties✅, Mock object support for web, data, REST APIs... ✅

* Spring Boot provided rich testing support.

    - `@SpringBootTest`: loads application context, dependency injection support, access data from Spring application.properties

### 🟥 Spring Boot Starter 

* We can add spring-boot-starter-test which includes a transitive dependency on JUnit 5

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

* If we look at the Maven dependency, we can see JUnit being listed:

    ![](screenshots/2023-04-24-14-21-35.png)

### 🟥 Spring Boot Test - Injecting Dependencies

```java
@SpringBootTest
public class ApplicationExampleTest {
    @Test
    void basicTest(){
        // ...
    }
}
```

* The `@SpringBootTest` loads up the application context, this opens up the oppurtunity to inject dependencies. Suppose we have a `StudentGrades` component:

```java
@Component
public class StudentGrades{
    // ...
}
```

* We can inject the dependency using `@Autowired`:

```java
@SpringBootTest
public class ApplicationExampleTest {
    @Autowired
    StudentGrades studentGrades;

    @Test
    void basicTest(){
        // ...
    }
}
```

### 🟥 Spring Boot Test - Accessing Properties

* We can also access application properties. Suppose we have the following properties:

```properties
info.school.name=luv2code
info.app.name=My super cool Gradebook
```

* We can access the properties witin out Test class:

```java
@SpringBootTest
public class ApplicationExampleTest {
    @Value("${info.school.name}")
    private String schoolName;

    @Value("${info.app.name}")
    private String appInfo;

    @Test
    void basicTest(){}
}
```

### 🟥 Spring Boot Test - Configuration

* You can implicitly use the base searched package if you place your test class in test package same as your main package. 

* E.g.:

    ![](screenshots/2023-04-24-15-00-04.png)

* Suppose we wanted to place our Test class not in the default leveraged package (e.g. com.luv2code.test). We then need to make an an explicit reference to the main SpringBoot class:

```java
@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {

}
```

### 🟥 Our Sample Project

* Suppose we have a student grading system which was coded by a previous employee. The code does NOT use ANY unit tests😱 We have been tasked with devloping the unit tests

<br>
<hr>

## 🟦 4.2 Project Setup

* I open [2.00-starting-project](/spring-boot-unit-testing-main/2.00-starting-project/)

    ![](screenshots/2023-04-24-15-10-19.png)    

* We have a `Student` interface:

```java
public interface Student {
    String studentInformation();
    String getFullName();
}
```

* A `CollegeStudent` implementation:

```java
public class CollegeStudent implements Student {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private StudentGrades studentGrades;
    public CollegeStudent() {}
    public CollegeStudent(String firstname, String lastname, String emailAddress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress; }
    // GETTERS AND SETTERS ...
    @Override
    public String toString() {
        return "CollegeStudent{" + "firstname='" +  + '\'' ", lastname='" + lastname + '\'' + ", emailAddress='" + emailAddress + '\'' + ", studentGrades=" + studentGrades +'}';
    }
    @Override
    public String studentInformation() {
        return getFullName() + " " + getEmailAddress();
    }
    @Override
    public String getFullName() {
        return getFirstname() + " " + getLastname();
    }
}
```

* A `StudentGrades` class:

```java
@Component
public class StudentGrades {
    List<Double> mathGradeResults;
    /*
     * CAN HAVE MULTIPLE DIFFERENT TYPES OF GRADES
     * FOR 2.x WE WILL ONLY HAVE A MATH GRADE
     *  */
    public StudentGrades() {
    }
    public StudentGrades(List<Double> mathGradeResults) {
        this.mathGradeResults = mathGradeResults;
        /*
        Add other subject grades here in future lessons
        */
    }
        /*
        Add other subject grades here in future lessons
        */

    public double addGradeResultsForSingleClass(List<Double> grades) {
        double result = 0;
        for (double i : grades) {
            result += i;
        }
        return result;
    }

    public double findGradePointAverage (List<Double> grades ) {
        int lengthOfGrades = grades.size();
        double sum = addGradeResultsForSingleClass(grades);
        double result = sum / lengthOfGrades;

        // add a round function
        BigDecimal resultRound = BigDecimal.valueOf(result);
        resultRound = resultRound.setScale(2, RoundingMode.HALF_UP);
        return resultRound.doubleValue();

    }

    public Boolean isGradeGreater(double gradeOne, double gradeTwo) {
        if (gradeOne > gradeTwo) {
            return true;
        }
        return false;
    }

    public Object checkNull(Object obj) {
        if ( obj != null ) {
            return obj;
        }
        return null;
    }

    public List<Double> getMathGradeResults() {
        return mathGradeResults;
    }

    public void setMathGradeResults(List<Double> mathGradeResults) {
        this.mathGradeResults = mathGradeResults;
    }

    @Override
    public String toString() {
        return "StudentGrades{" +
                "mathGradeResults=" + mathGradeResults +
                '}';
    }
}
```

* And the Spring Boot Application class:

```java
@SpringBootApplication
public class MvcTestingExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(MvcTestingExampleApplication.class, args); }
	@Bean(name = "collegeStudent")
	@Scope(value = "prototype")
	CollegeStudent getCollegeStudent() {
		return new CollegeStudent();
	}
}
```

<br>
<hr>

## 🟦 4.3 Fixing the project

### 🟥 Setting Up Test

* I create a new copy of the 2.00-starter-project and place it [here](/Demos/demo-07-spring_boot_unit_testing/)

* I add the `spring-boot-starter-test` dependency into the POM:

```xml
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
		</dependency>
	</dependencies>
```

* I create a new directory and package:

    ![](screenshots/2023-04-25-08-56-01.png)

* I define an `ApplicationExampleTest` class:

```java
@SpringBootTest
public class ApplicationExampleTest{
    @Test
    void basicTest(){}
}
```

* I change the package of the test class to `com.luv2code.component` to match consistency of the main application's package. The test passes as expected

* ⚠️ IF we had issues with the test class not being able to load the configuration, THEN we can use the `@SpringBootTest` annotation and explicitly specify the SpringBoot application class: ⚠️

```java
@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {
    @Test
    void basicTest(){}
} 
```


### 🟥 Accessing Application Properties + Injecting Dependencies

* We can access values from the `application.properties`  file using the `@Value` annotation.

* E.g. suppose we have a property labeled info.app.name, we can set the value of a field and write a unit test to verify its value: 

```java
@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {
    
    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;

    @Test
    void checkAppInfo(){
        assertEquals(appInfo, "My Super Cool Gradebook");
    }
}
```

* I update my test class so it has the properties and dependencies injected:

```java
@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {
    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Test
    void checkAppInfo(){
        assertEquals(appInfo, "My Super Cool Gradebook");
    }
}
```

* I create a `BeforeEach` method which initialises a Student object and increments the count:

```java
@BeforeEach
void beforeEach(){
    count = count++;
    System.out.println("Testing: "+appInfo + " which is "+appDescription
    + " Version: " + appVersion + "Executing Test method: "+ count);

    student.setFirstname("Shiv");
    student.setLastname("Kumar");
    student.setEmailAddress("shiv.kumar@luv2code.com");
    studentGrades.setMathGradeResults(Arrays.asList(100.0, 85.0, 76.50, 91.75));
}
```

### 🟥 assertEquals and assertNotEquals

* I add a new test for adding student grades:

```java
    @Test
    @DisplayName("Add grade results for student grades")
    void addGradeResultsForStudentGrades(){
        assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()));
    }
```

* Here is an assertNotEqualsTest:

```java
    @Test
    @DisplayName("Add grade results for student grades not equal")
    void addGradeResultsForStudentGradesAssertNotEqual(){
        assertNotEquals(100, studentGrades.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()));
        // actual is 353.25
    }
```

* Both tests pass as expected!

### 🟥 assertTrue, assertFalse and assertNotNull

* Here is a test which tests the `isGradeGreater`

```java
    @Test
    @DisplayName("Is grades greater?")
    void isGradeGreaterStudentGrades(){
        assertTrue(studentGrades.isGradeGreater(999, 1));
        //  Implementation:
        //  if (gradeOne > gradeTwo) {
        //      return true;
        //  }
        //      return false;
    }
```

* Here is a test which asserts given object is not null:

```java
    @Test
    @DisplayName("Check Null for student grades")
    void checkNullForGrades(){
        assertNotNull(studentGrades.getMathGradeResults());
    }
```

### 🟥 Prototype Beans

* We can create prototype beans like in the following test:

```java
    @DisplayName("Create student without grade init")
    @Test
    void createStudentWithoutGradesInit(){
        // autowired ApplicationContext into this class
        CollegeStudent studentTwo = context.getBean(
                "collegeStudent", CollegeStudent.class
        );
        studentTwo.setFirstname("Shiv");
        studentTwo.setLastname("Kumar");
        studentTwo.setEmailAddress("shiv.kumar@luv2code.com");
        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades())); // passes!
    }
```

* We can verify the autowired `student` is not the same as the prototype through `assertNotSame`@:

```java
    @DisplayName("Verify students are prototypes")
    @Test
    void verifyStudentArePrototypes(){
        // CollegeStudent is not a service!
        CollegeStudent studentTwo = context.getBean(
                "collegeStudent", CollegeStudent.class
        );
        assertNotSame(student, studentTwo);
    }
```

* The `assertAll` let's us asserts that all assertions hold true, we use a lambda expression with with:

```java
@DisplayName("Using assertAll")
@Test
void usingAssertAll(){
    assertAll(
        () -> assertEquals( true, true),
        () -> assertEquals(false,false)
    );
}
```



## 🟦 H2

### 🟥 H3

#### 🟧 H4 
