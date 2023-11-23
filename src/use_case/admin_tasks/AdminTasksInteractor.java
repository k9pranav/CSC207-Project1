package use_case.admin_tasks;

import entity.CourseTask;
import entity.StudentTask;

import java.text.SimpleDateFormat;

public class AdminTasksInteractor implements AdminTasksInputBoundary {
    final AdminTasksDataAccessInterface tasksDAO;
    final AdminTasksOutputBoundary tasksPresenter;

    public AdminTasksInteractor(AdminTasksDataAccessInterface tasksDAO, AdminTasksOutputBoundary tasksPresenter){
        this.tasksDAO = tasksDAO;
        this.tasksPresenter = tasksPresenter;
    }
    @Override
    public void execute(AdminTasksInputData inputData){
        if (("exit").equals(inputData.getButtonPressed())){
            tasksPresenter.prepareExit(inputData.getLoggedIn());
        } else if(("new task").equals(inputData.getButtonPressed())){
            CourseTask newTask = new CourseTask("", "", new SimpleDateFormat(), 0.0F);
            // will assign values to the parameters, and assign to a course in edit task view
            AdminTasksOutputData outputData = new AdminTasksOutputData(newTask, inputData.getLoggedIn());
            tasksPresenter.prepareEditTaskView(outputData);
        } else {
           String taskName = inputData.getButtonPressed();
           CourseTask currentTask = inputData.getLoggedIn().getTaskFromName(taskName);
           AdminTasksOutputData outputData = new AdminTasksOutputData(currentTask, inputData.getLoggedIn());
           tasksPresenter.prepareEditTaskView(outputData);
        }
    }
}
