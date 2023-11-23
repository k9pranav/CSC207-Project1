package interface_adapter.student_courses;

import entity.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.student_logged_in.StudentLoggedInState;
import interface_adapter.student_logged_in.StudentLoggedInViewModel;
import use_case.student_courses.StudentCoursesOutputBoundary;
import use_case.student_courses.StudentCoursesOutputData;

public class StudentCoursesPresenter implements StudentCoursesOutputBoundary {
private final ViewManagerModel viewManagerModel;
private final StudentCourseTasksViewModel tasksViewModel;
private final StudentCoursesViewModel coursesViewModel;
private final StudentLoggedInViewModel homePageViewModel;

public StudentCoursesPresenter(ViewManagerModel viewManagerModel, StudentCoursesViewModel coursesViewModel, StudentCourseTasksViewModel tasksViewModel, StudentLoggedInViewModel homePageViewModel){
    this.viewManagerModel = viewManagerModel;
    this.tasksViewModel = tasksViewModel;
    this.homePageViewModel = homePageViewModel;
    this.coursesViewModel = coursesViewModel;
}

    @Override
    public void prepareAverage(Float avg, Student student) {
        // prepare popup with average
        StudentCoursesState coursesState = coursesViewModel.getState();

        String average = avg.toString();
        coursesState.setAverage(average);
        coursesViewModel.firePropertyChangedPopup();
}

    @Override
    public void prepareCourseTasksView(StudentCoursesOutputData outputData) {
        StudentCourseTasksState tasksState = tasksViewModel.getState();
        this.tasksViewModel.setState(tasksState);
        this.tasksViewModel.getState().setLoggedInUser(outputData.getLoggedInUser());
        this.tasksViewModel.getState().setTasks(outputData.getTasks());
        tasksViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(tasksViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareExit(Student student) {
        StudentLoggedInState loggedInState = homePageViewModel.getState();
        this.homePageViewModel.getState(loggedInState);
        this.homePageViewModel.getState().setLoggedInUser(student);
        homePageViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        // goes back to home page
    }
}
