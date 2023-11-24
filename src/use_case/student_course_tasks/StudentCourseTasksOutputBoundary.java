package use_case.student_course_tasks;

import entity.Student;

public interface StudentCourseTasksOutputBoundary {
    void prepareTaskPopup(StudentCourseTasksOutputData outputData);
    void prepareExit(Student student);
}
