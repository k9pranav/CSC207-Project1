package use_case.admin_course_tasks;

import entity.Course;
import entity.CourseTask;
import use_case.student_course_tasks.StudentCourseTasksInputData;
import use_case.student_course_tasks.StudentCourseTasksOutputData;

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
        }
    }

    @Override
    public void executePopup(AdminCourseTasksInputData inputData) {
        CourseTask currentTask = (CourseTask) inputData.getLoggedIn().getTaskFromName(inputData.getButtonPressed());
        String name = currentTask.getTaskName();
        SimpleDateFormat deadline = currentTask.getDeadLine();
        Float weight = currentTask.getWeight();
        Course course = currentTask.getCourse();
        String type = currentTask.getType();
        AdminCourseTasksPopupOutputData outputData = new AdminCourseTasksPopupOutputData(name, deadline, weight, course, type);
        tasksPresenter.prepareTaskPopup(outputData);
    }

}
