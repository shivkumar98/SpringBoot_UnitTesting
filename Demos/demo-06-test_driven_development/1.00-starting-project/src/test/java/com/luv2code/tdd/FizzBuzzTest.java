package com.luv2code.tdd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FizzBuzzTest {

    @Test
    void testForDivisibleByThree(){
        assertEquals("Fizz", FizzBuzz.compute(3), "3 should return Fizz");
    }

    @Test
    void testForDivisibleByFive(){
        assertEquals("Buzz", FizzBuzz.compute(5), "5 should return Buzz");
    }

    @Test
    void testForDivisbleBy3And5(){
        assertEquals("FizzBuzz", FizzBuzz.compute(15), "15 should return FizzBuzz");
    }

    @Test
    void testForNonFizzBuzzNumber(){
        assertEquals("1", FizzBuzz.compute(1), "1 should return 1");
    }

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

    @ParameterizedTest(name = "value={0}, expected={1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    void test(int value, String expected){
        assertEquals(FizzBuzz.compute(value), expected);
    }

    @ParameterizedTest(name="value:{0}, expected:{1}")
    @CsvFileSource(resources = "/large-test-data.csv")
    void testWithLargeData(int value, String expected){
        assertEquals(FizzBuzz.compute(value), expected);
    }

}
