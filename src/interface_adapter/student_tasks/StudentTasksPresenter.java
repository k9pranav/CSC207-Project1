package interface_adapter.student_tasks;

import entity.Student;
import entity.StudentTask;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_landing_page.AdminLandingPageState;
import interface_adapter.admin_logged_in.AdminLoggedInState;
import interface_adapter.edit_student_task.EditStudentTaskState;
import interface_adapter.edit_student_task.EditStudentTaskViewModel;
import interface_adapter.login_admin.LoginAdminState;
import interface_adapter.student_logged_in.StudentLoggedInViewModel;
import use_case.student_tasks.StudentCourseTasksOutputData;
import use_case.student_tasks.StudentTasksOutputBoundary;
import use_case.student_tasks.StudentTasksOutputData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StudentTasksPresenter implements StudentTasksOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final StudentTasksViewModel tasksViewModel;
    private final StudentLoggedInViewModel homePageViewModel;
    private final EditStudentTaskViewModel editStudentTaskViewModel;
    // when they press create new task button

    public StudentTasksPresenter(ViewManagerModel viewManagerModel, StudentTasksViewModel tasksViewModel, StudentLoggedInViewModel homePageViewModel, EditStudentTaskViewModel editStudentTaskViewModel){
        this.viewManagerModel = viewManagerModel;
        this.tasksViewModel = tasksViewModel;
        this.homePageViewModel = homePageViewModel;
        this.editStudentTaskViewModel = editStudentTaskViewModel;
    }

    @Override
    public void prepareTaskPopup(StudentCourseTasksOutputData outputData){

    }
    @Override
    public void prepareTaskPopup(StudentTasksOutputData outputData){
        StudentTasksState tasksState = tasksViewModel.getState();

        String taskInfo = "";
        taskInfo = taskInfo + "Task Name: " + outputData.getName() + "\n";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskInfo = taskInfo + "Deadline: " + dateFormat.format(outputData.getDeadline());

        tasksState.setCurrentTaskInfo(taskInfo);
        tasksViewModel.firePropertyChanged();
        // how to add a button in the popup to lead to Edit Task View :(
    }
    @Override
    public void prepareExit(Student student){
        // need to figure out how to differentiate between this firePropertyChanged() in the StudentTaskView
        AdminLoggedInState loggedInState = homePageViewModel.getState();
        this.homePageViewModel.setState(loggedInState);
        this.homePageViewModel.getState().setLoggedInUser(student);
        homePageViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        // goes back to home page with calendar
    }
    @Override
    public void prepareEditTaskView(StudentTask editTask){
        EditStudentTaskState editTaskState = editStudentTaskViewModel.getState();
        this.editStudentTaskViewModel.setState(editTaskState);
        this.editStudentTaskViewModel.getState().setCurrentTask();
        // sets the current task being edited
        editStudentTaskViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(editStudentTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
