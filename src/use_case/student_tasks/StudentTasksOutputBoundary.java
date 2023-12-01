package use_case.student_tasks;

import entity.Student;
import entity.StudentTask;

public interface StudentTasksOutputBoundary {
    void prepareTaskPopup(StudentTasksOutputData outputData);
    void prepareExit(Student student);
    void prepareEditTaskView(StudentTask newTask, Student student);
}
