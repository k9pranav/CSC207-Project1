package use_case.student_tasks;

public class StudentTasksInteractor implements StudentTasksInputBoundary {
    // execute, executeExit, executeNewTask
    final StudentTasksDataAccessInterface tasksDAO;
    final StudentTasksOutputBoundary tasksPresenter;

    public StudentTasksInteractor(StudentTasksDataAccessInterface tasksDAO, StudentTasksOutputBoundary tasksPresenter){
        this.tasksDAO = tasksDAO;
        this.tasksPresenter = tasksPresenter;
    }

    @Override
    public void execute(StudentTasksInputData tasksInputData){
        // opening expanded task view of selected task

    }
    @Override
    public void executeExit(String str){
        // x button pressed, go back to home screen
    }
    @Override
    public void executeNewTask(String str){
        // create new task in edit task view
    }
}

