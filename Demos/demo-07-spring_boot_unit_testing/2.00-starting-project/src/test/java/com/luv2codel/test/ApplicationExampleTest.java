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
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Autowired
    ApplicationContext context;

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

    @Test
    @DisplayName("Check Null for student grades")
    void checkNullForGrades(){
        assertNotNull(studentGrades.getMathGradeResults());
    }

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

    @DisplayName("Verify students are prototypes")
    @Test
    void verifyStudentArePrototypes(){
        // CollegeStudent is not a service!
        CollegeStudent studentTwo = context.getBean(
                "collegeStudent", CollegeStudent.class
        );
        assertNotSame(student, studentTwo);
    }

    @DisplayName("Using assertAll")
    @Test
    void usingAssertAll(){
        assertAll(
            () -> assertEquals( true, true),
            () -> assertEquals(false,false)
        );
    }
}
