package interface_adapter.admin_tasks;

import entity.Admin;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_course_tasks.AdminCourseTasksState;
import interface_adapter.admin_logged_in.AdminLoggedInState;
import interface_adapter.admin_logged_in.AdminLoggedInViewModel;
import interface_adapter.edit_course_task.EditCourseTaskState;
import interface_adapter.edit_course_task.EditCourseTaskViewModel;
import use_case.admin_tasks.AdminTasksOutputBoundary;
import use_case.admin_tasks.AdminTasksOutputData;
import use_case.admin_tasks.AdminTasksOutputDataPopup;

import java.text.SimpleDateFormat;

public class AdminTasksPresenter implements AdminTasksOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final AdminTasksViewModel tasksViewModel;
    private final AdminLoggedInViewModel homePageViewModel;
    private final EditCourseTaskViewModel editCourseTaskViewModel;

    public AdminTasksPresenter(ViewManagerModel viewManagerModel, AdminTasksViewModel tasksViewModel, AdminLoggedInViewModel homePageViewModel, EditCourseTaskViewModel editCourseTaskViewModel){
        this.viewManagerModel = viewManagerModel;
        this.tasksViewModel = tasksViewModel;
        this.homePageViewModel = homePageViewModel;
        this.editCourseTaskViewModel = editCourseTaskViewModel;
    }

    @Override
    public void prepareExit(Admin admin){
        AdminLoggedInState loggedInState = homePageViewModel.getState();
        this.homePageViewModel.setState(loggedInState);
        this.homePageViewModel.getState().setLoggedInUser(admin);
        homePageViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareEditTaskView(AdminTasksOutputData edit){
        EditCourseTaskState editCourseTaskState = editCourseTaskViewModel.getState();
        editCourseTaskViewModel.setState(editCourseTaskState);
        editCourseTaskViewModel.getState().setLoggedIn(edit.getLoggedIn());
        editCourseTaskViewModel.getState().setCurrentTask(edit.getTask());
        editCourseTaskViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(editCourseTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareTaskPopup(AdminTasksOutputDataPopup outputData) {
        AdminTasksState tasksState = tasksViewModel.getState();

        String taskInfo = "";
        taskInfo = taskInfo + "Task Name: " + outputData.getName() + "\n";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskInfo = taskInfo + "Deadline: " + dateFormat.format(outputData.getDeadline())+ "\n";
        taskInfo = taskInfo + "Type: " + outputData.getType() + "\n";
        taskInfo = taskInfo + "Weight: " + outputData.getWeight().toString() + "\n";
        taskInfo = taskInfo + "Course: " + outputData.getCourseCode() + ": " + outputData.getCourseName() + "\n";

        tasksState.setCurrentTaskInfo(taskInfo);
        tasksViewModel.firePropertyChangedPopup();
    }
}

