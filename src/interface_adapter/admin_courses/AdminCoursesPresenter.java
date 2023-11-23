package interface_adapter.admin_courses;

import entity.Admin;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_course_tasks.AdminCourseTasksState;
import interface_adapter.admin_course_tasks.AdminCourseTasksViewModel;
import interface_adapter.admin_logged_in.AdminLoggedInState;
import interface_adapter.admin_logged_in.AdminLoggedInViewModel;
import interface_adapter.student_course_tasks.StudentCourseTasksState;
import interface_adapter.student_logged_in.StudentLoggedInState;
import use_case.admin_courses.AdminCoursesOutputBoundary;
import use_case.admin_courses.AdminCoursesOutputData;

import javax.swing.text.View;

public class AdminCoursesPresenter implements AdminCoursesOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final AdminCourseTasksViewModel tasksViewModel;
    private final AdminCoursesViewModel coursesViewModel;
    private final AdminLoggedInViewModel homePageViewModel;

    public AdminCoursesPresenter(ViewManagerModel viewManagerModel, AdminCoursesViewModel coursesViewModel, AdminCourseTasksViewModel tasksViewModel, AdminLoggedInViewModel homePageViewModel){
        this.viewManagerModel = viewManagerModel;
        this.tasksViewModel = tasksViewModel;
        this.homePageViewModel = homePageViewModel;
        this.coursesViewModel = coursesViewModel;
    }

    @Override
    public void prepareCourseTasksView(AdminCoursesOutputData outputData){
        AdminCourseTasksState tasksState = tasksViewModel.getState();
        this.tasksViewModel.setState(tasksState);
        this.tasksViewModel.getState().setLoggedInUser(outputData.getLoggedInUser());
        this.tasksViewModel.getState().setCourse(outputData.getCourse());
        // set current course and the list of tasks for this course
        this.tasksViewModel.getState().setTasks(outputData.getTasks());
        tasksViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(tasksViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
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
}
