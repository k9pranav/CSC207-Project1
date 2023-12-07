package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    Admin testAdmin = Admin("Pranav", "Kansal", "MyPassword",
            "MyPassword", "pranav.kansal@mail.utoronto.ca");

    Course testCourse = new Course("Software Design", "CSC207", 2023, "F",
            "Design Patterns", testAdmin);

    @Test
    void getCourseName() {
        assertEquals("Software Design", testCourse.getCourseName());
    }

    @Test
    void getCourseCode() {
        assertEquals("CSC207", testCourse.getCourseCode());
    }

    @Test
    void getCourseDescription() {
        assertEquals("Design Patterns", testCourse.getCourseDescription());
    }

    @Test
    void getCourseAdmin() {
        assertEquals(testCourse, testCourse.getCourseAdmin());
    }

    @Test
    void getCourseYear() {
        assertEquals(2023, testCourse.getCourseYear());
    }

    @Test
    void getCourseSession() {
        assertEquals("F", testCourse.getCourseSession());
    }

    @Test
    void getStudentsEnrolled() {
        assertEquals([], testCourse.getStudentsEnrolled();)
    }


}