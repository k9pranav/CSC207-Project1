package interface_adapter.student_tasks;

import entity.Student;
import entity.Task;
import use_case.student_tasks.StudentTasksInputBoundary;
import use_case.student_tasks.StudentTasksInputData;

public class StudentTasksController {
    private final StudentTasksInputBoundary interactor;

    public StudentTasksController(StudentTasksInputBoundary interactor){
        this.interactor = interactor;
    }
    public void execute(String buttonPressed) {
        if ("new task".equals(buttonPressed)){
            interactor.executeNewTask(buttonPressed);
        }
        else if ("exit".equals(buttonPressed)){
            interactor.executeExit(buttonPressed);
        }
    }
    public void executeTask(String taskName, Student loggedIn){
        // double click detected
        StudentTasksInputData inputData = new StudentTasksInputData(taskName, loggedIn);
        interactor.execute(inputData);
    }
}
