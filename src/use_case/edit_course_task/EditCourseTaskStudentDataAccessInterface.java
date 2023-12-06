package use_case.edit_course_task;

import entity.Course;
import entity.Student;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;

public interface EditCourseTaskStudentDataAccessInterface {

    // boolean doesAdminHaveCourse(Course course);
    //Return true whether the admin has the course or not

    void createTask(String taskName, String taskType,
                    SimpleDateFormat taskDeadline,
                    Course taskCourse,
                    float taskWeight, Student student) throws IOException, GeneralSecurityException;
    //Returns true or false
}
