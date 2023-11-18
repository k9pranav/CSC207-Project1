package use_case.student_tasks;

public interface StudentTasksInputBoundary {
    void execute(StudentTasksInputData a);
    void executeExit(String s);
    void executeNewTask(String s);
}
