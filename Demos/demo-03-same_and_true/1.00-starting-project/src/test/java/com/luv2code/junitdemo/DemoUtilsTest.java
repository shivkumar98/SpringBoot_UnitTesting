package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
public class DemoUtilsTest {

    DemoUtils demoUtils= new DemoUtils();

    @Test
    @DisplayName("This tests shows equality")
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
