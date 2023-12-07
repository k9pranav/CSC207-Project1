package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTaskTest {

    CourseTask newCourseTask = CourseTask("Quiz 1", "Examination", "12/05/2023", 0.3);


    @Test
    void getWeight() {
        assertEquals(0.3, newCourseTask.getWeight());
    }

    @Test
    void getDeadline() {
        assertEquals("12/03/2023", newCourseTask.getDeadline());
    }

}