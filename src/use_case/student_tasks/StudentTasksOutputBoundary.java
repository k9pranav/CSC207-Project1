package use_case.student_tasks;

import entity.StudentTask;

public interface StudentTasksOutputBoundary {
    void prepareTaskPopup(StudentCourseTasksOutputData outputData);
    void prepareTaskPopup(StudentTasksOutputData outputData);
    void prepareExit();
    void prepareEditTaskView(StudentTask newTask);
}
