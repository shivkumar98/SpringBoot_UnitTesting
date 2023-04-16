package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
public class DemoUtilsTest {

    DemoUtils demoUtils= new DemoUtils();

    @Order(1)
    @Test
    @DisplayName("This tests shows equality")
    void testEqualsAndNotEquals(){
        System.out.println("Running test: testEqualsAndNotEquals");

        // using assertEquals and NotEquals:
        assertEquals(6, demoUtils.add(2,4), "2+4 should equal 6");
        assertNotEquals(6, demoUtils.add(1,9), "1+9 should not be 6");
    }

    @Order(-33)
    @Test
    void testNullAndNotNull() {
        System.out.println("Running test: testNullAndNotNull");


        String str1 = null; String str2 = "luv2code";

        assertNull(demoUtils.checkNull(str1));
        assertNotNull(demoUtils.checkNull(str2));
    }


    @Test
    void testTrueAndFalse(){
        assertTrue(1==1, "one must equal one!"); // PASSES!
        assertFalse(1>1, "one cannot be greater than one!"); // PASSES
    }

    @Test
    void testSameAndNotSame(){
        String str = "luv2code";
        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate());
        assertNotSame(demoUtils.getAcademy(), str);
    }

    @Test
    void arrayIsDeeplyEqual(){
        String[] firstThreeLetters = {"A", "B", "C"};
        assertArrayEquals(firstThreeLetters, new String[] {"A", "B", "C"}); // PASSES!

        // assertArrayEquals(firstThreeLetters, new String[] {"A", "C", "B"}); // FAILS!!
    }

    @Test
    void iterableIsDeeplyEqual(){
        List<String> list = List.of("Shiv", "Likes", "Java");
        assertIterableEquals(list, List.of("Shiv","Likes", "Java")); // PASSES!
        // assertIterableEquals(list, List.of("Shiv","Likes")); // FAILS!
    }

    @Test
    void LinesShouldMatch(){
        List<String> list = List.of("Shiv", "Likes", "Java");
        assertLinesMatch(list, List.of("Shiv","Likes","Java")); // PASSES!!
        // assertLinesMatch(list, List.of("Shiv","Java","Likes")); // FAILS!!
    }

    @Test
    void testThrowsAndDoesNotThrow() {
        assertThrows(Exception.class,
                     ()->{ demoUtils.throwException(-1); },
                   "An exception should be thrown"); // PASSES!!

        assertThrows(Exception.class,
                ()-> {throw new RuntimeException();},
                "Should throw Exception class"); // PASSES

        /* assertThrows(RuntimeException.class,
                ()-> { throw new Exception();},
                "Should throw RuntimeException class"); // FAILS
         */

        assertDoesNotThrow(()-> {demoUtils.throwException(1);} ); // PASSES!!

    }

    @Test
    void testTimeOutAndTimeOutPremptively(){
        assertTimeout(Duration.ofSeconds(3), ()-> {demoUtils.checkTimeout(); } ); // PASSES!
        /* CONSOLE:
            I am going to sleep
            Sleeping over
         */

        // assertTimeout(Duration.ofSeconds(2), ()-> {demoUtils.checkTimeout(); } ); // FAILS!
        /* CONSOLE:
            I am going to sleep
            Sleeping over
            org.opentest4j.AssertionFailedError: execution exceeded timeout of 2000 ms by 6 ms
         */

        // assertTimeoutPreemptively(Duration.ofSeconds(1), ()->{demoUtils.checkTimeout();} ); // FAILS!
        /* CONSOLE
            I am going to sleep
            org.opentest4j.AssertionFailedError: execution timed out after 1000 ms
         */

    }

    @Test
    void purposlyFailingTest(){
        assertTrue(false);
    }


    /*

    @BeforeAll
    static void setUpBeforeAllMethods(){ // MUST BE STATIC
        System.out.println("@BeforeAll only executes once before all methods");
    }

    @AfterAll
    static void tearDownAfterAll() {
        System.out.println("@AfterAll only executes after all methods");
    }

    @BeforeEach
    void setUpBefore(){
        demoUtils = new DemoUtils();
        System.out.println("BeforeEach executes before each test method");
    }

    @AfterEach
    void tearDownAfterEach(){
        System.out.println("Running @AfterEach");
    }

     */
}
