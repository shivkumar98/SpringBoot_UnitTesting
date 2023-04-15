package com.luv2code.junitdemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DemoUtilsTest {
    @Test
    void testEqualsAndNotEquals(){
        // setup:
        DemoUtils demoUtils = new DemoUtils();

        // using assertEquals and NotEquals:
        assertEquals(6, demoUtils.add(2,4), "2+4 should equal 6");
        assertNotEquals(6, demoUtils.add(1,9), "1+9 should not be 6");
    }

    @Test
    void testNullAndNotNull() {
        DemoUtils demoUtils = new DemoUtils();

        String str1 = null; String str2 = "luv2code";

        assertNull(demoUtils.checkNull(str1));
        assertNotNull(demoUtils.checkNull(str2));
    }
}
