package interface_adapter.student_tasks;

import entity.StudentTask;
import interface_adapter.ViewManagerModel;
import interface_adapter.edit_student_task.EditStudentTaskViewModel;
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
    }
    @Override
    public void prepareExit(){
        StudentTasksState tasksState = tasksViewModel.getState();
        this.tasksViewModel.setState(tasksState);
        homePageViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        // goes back to home page with calendar
    }
    @Override
    public void prepareEditTaskView(StudentTask editTask){
        // editTask is the current task we need to open the editTask view for
    }
}
