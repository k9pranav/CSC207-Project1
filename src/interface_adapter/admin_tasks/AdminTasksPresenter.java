package interface_adapter.admin_tasks;

import entity.Admin;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_logged_in.AdminLoggedInState;
import interface_adapter.admin_logged_in.AdminLoggedInViewModel;
import interface_adapter.edit_course_task.EditCourseTaskState;
import interface_adapter.edit_course_task.EditCourseTaskViewModel;
import use_case.admin_tasks.AdminTasksOutputBoundary;
import use_case.admin_tasks.AdminTasksOutputData;

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
}

