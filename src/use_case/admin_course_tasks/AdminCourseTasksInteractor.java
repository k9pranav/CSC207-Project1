package use_case.admin_course_tasks;

import entity.CourseTask;

import java.text.SimpleDateFormat;

public class AdminCourseTasksInteractor implements AdminCourseTasksInputBoundary{
    final AdminCourseTasksDataAccessInterface tasksDAO;
    final AdminCourseTasksOutputBoundary tasksPresenter;

    public AdminCourseTasksInteractor(AdminCourseTasksDataAccessInterface tasksDAO, AdminCourseTasksOutputBoundary tasksPresenter){
        this.tasksDAO = tasksDAO;
        this.tasksPresenter = tasksPresenter;
    }

    @Override
    public void execute(AdminCourseTasksInputData inputData){
        if(("exit").equals(inputData.getButtonPressed())){
            tasksPresenter.prepareExit(inputData.getLoggedIn());
        } else if(("new task").equals(inputData.getButtonPressed())){
            CourseTask newTask = new CourseTask("", "", new SimpleDateFormat(), 0.0F);
            // the edit task view checks for duplicate name and stuff and actually enters the details into the new task
            AdminCourseTasksOutputData outputData = new AdminCourseTasksOutputData(newTask, inputData.getLoggedIn());
            tasksPresenter.prepareEditTaskView(outputData);
        } else {
            // edit an existing task
            String taskName = inputData.getButtonPressed();
            CourseTask currentTask = inputData.getLoggedIn().getTaskFromName(taskName);
            AdminCourseTasksOutputData outputData = new AdminCourseTasksOutputData(currentTask, inputData.getLoggedIn());
            tasksPresenter.prepareEditTaskView(outputData);
        }
    }
}
