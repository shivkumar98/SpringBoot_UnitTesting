package com.luv2code.tdd;

import org.junit.jupiter.api.Test;

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
}
