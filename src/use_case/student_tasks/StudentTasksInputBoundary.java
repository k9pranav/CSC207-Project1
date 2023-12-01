package use_case.student_tasks;

public interface StudentTasksInputBoundary {
    void execute(StudentTasksInputData a);
    void executeExit(StudentTasksInputData a);
    void executeNewTask(StudentTasksInputData a);
}
