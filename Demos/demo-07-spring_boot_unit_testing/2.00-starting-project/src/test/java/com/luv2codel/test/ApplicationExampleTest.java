package com.luv2codel.test;

import java.util.ArrayList;
import java.util.Arrays;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    @Test
    void checkAppInfo(){
        assertEquals(appInfo, "My Super Cool Gradebook");
    }

    @Test
    @DisplayName("Add grade results for student grades")
    void addGradeResultsForStudentGrades(){
        assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()));
    }


    @Test
    @DisplayName("Add grade results for student grades not equal")
    void addGradeResultsForStudentGradesAssertNotEqual(){
        assertNotEquals(100, studentGrades.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()));
        // actual is 353.25
    }
}
