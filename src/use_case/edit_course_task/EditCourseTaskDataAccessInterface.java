package use_case.edit_course_task;

import entity.Course;

import java.text.SimpleDateFormat;

public interface EditCourseTaskDataAccessInterface {

    boolean doesAdminHaveCourse(Course course);
    //Return true whether the admin has the course or not

    void createTask(String taskName, String taskType,
                    SimpleDateFormat taskDeadline,
                    Course taskCourse,
                    float taskWeight);
    //Returns true or false
}
