package interface_adapter.student_tasks;

import entity.Student;
import entity.StudentTask;
import interface_adapter.ViewManagerModel;
import interface_adapter.edit_student_task.EditStudentTaskState;
import interface_adapter.edit_student_task.EditStudentTaskViewModel;
import interface_adapter.loggedin_student.LoggedInStudentState;
import interface_adapter.loggedin_student.LoggedinStudentViewModel;
import interface_adapter.student_logged_in.StudentLoggedInState;
import interface_adapter.student_logged_in.StudentLoggedInViewModel;
import use_case.student_tasks.StudentTasksOutputData;
import use_case.student_tasks.StudentTasksOutputBoundary;

import java.text.SimpleDateFormat;

public class StudentTasksPresenter implements StudentTasksOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final StudentTasksViewModel tasksViewModel;
    private final LoggedinStudentViewModel homePageViewModel;
    private final EditStudentTaskViewModel editStudentTaskViewModel;
    // when they press create new task button

    public StudentTasksPresenter(ViewManagerModel viewManagerModel, StudentTasksViewModel tasksViewModel, LoggedinStudentViewModel homePageViewModel, EditStudentTaskViewModel editStudentTaskViewModel){
        this.viewManagerModel = viewManagerModel;
        this.tasksViewModel = tasksViewModel;
        this.homePageViewModel = homePageViewModel;
        this.editStudentTaskViewModel = editStudentTaskViewModel;
    }

    @Override
    public void prepareTaskPopup(StudentTasksOutputData outputData){
        StudentTasksState tasksState = tasksViewModel.getState();

        String taskInfo = "";
        taskInfo = taskInfo + "Task Name: " + outputData.getName() + "\n";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskInfo = taskInfo + "Deadline: " + dateFormat.format(outputData.getDeadline())+ "\n";
        taskInfo = taskInfo + "Weight: " + outputData.getWeight().toString() + "\n";
        taskInfo = taskInfo + "Grade: " + outputData.getGrade().toString() + "\n";
        taskInfo = taskInfo + "Course: " + outputData.getCourseCode() + ": " + outputData.getCourseName() + "\n";

        tasksState.setCurrentTaskInfo(taskInfo);
        tasksViewModel.firePropertyChangedPopup();
    }
    @Override
    public void prepareExit(Student student){
        LoggedInStudentState loggedInState = homePageViewModel.getState();
        this.homePageViewModel.setState(loggedInState);
        //TODO Adrien: this.homePageViewModel.getState().setLoggedInUser(student);
        // should set it to the currently logged in user's home page
        homePageViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        // goes back to home page
    }
    @Override
    public void prepareEditTaskView(StudentTask editTask, Student student) {
        /*TODO:NADJA fix edit task stuff
        EditStudentTaskState editTaskState = editStudentTaskViewModel.getState();
        this.editStudentTaskViewModel.setState(editTaskState);
        this.editStudentTaskViewModel.getState().setCurrentTask(editTask);
        // sets the current task being edited
        editStudentTaskViewModel.getState().setLoggedIn(student);
        editStudentTaskViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(editStudentTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged()
         */
    }
}
