package use_case.student_course_tasks;

import interface_adapter.student_course_tasks.StudentCourseTasksController;

public interface StudentCourseTasksInputBoundary {
    void executeExit(StudentCourseTasksInputData a);
    void executePopup(StudentCourseTasksInputData a);
}
