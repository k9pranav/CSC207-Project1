package use_case.admin_tasks;

import entity.Course;
import entity.CourseTask;
import entity.StudentTask;
import use_case.admin_course_tasks.AdminCourseTasksPopupOutputData;

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
            String name = inputData.getButtonPressed();
            CourseTask currentTask = inputData.getLoggedIn().getTaskFromName(name);
            SimpleDateFormat deadline = currentTask.getDeadLine();
            Float weight = currentTask.getWeight();
            Course course = currentTask.getCourse();
            String type = currentTask.getType();
            AdminTasksOutputDataPopup outputData = new AdminTasksOutputDataPopup(name, deadline, weight, course, type);
            tasksPresenter.prepareTaskPopup(outputData);
        }
    }
}
