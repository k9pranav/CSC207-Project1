package interface_adapter.student_course_tasks;

import entity.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.student_courses.StudentCoursesState;
import interface_adapter.student_courses.StudentCoursesViewModel;
import use_case.student_course_tasks.StudentCourseTasksOutputBoundary;
import use_case.student_course_tasks.StudentCourseTasksOutputData;

import java.text.SimpleDateFormat;

public class StudentCourseTasksPresenter implements StudentCourseTasksOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final StudentCourseTasksViewModel tasksViewModel;
    private final StudentCoursesViewModel coursesViewModel;

    public StudentCourseTasksPresenter(ViewManagerModel viewManagerModel, StudentCourseTasksViewModel tasksViewModel, StudentCoursesViewModel coursesViewModel){
        this.viewManagerModel = viewManagerModel;
        this.tasksViewModel = tasksViewModel;
        this.coursesViewModel = coursesViewModel;
    }

    @Override
    public void prepareTaskPopup(StudentCourseTasksOutputData outputData){
        StudentCourseTasksState tasksState = tasksViewModel.getState();

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
        StudentCoursesState coursesState = coursesViewModel.getState();
        this.coursesViewModel.setState(coursesState);
        this.coursesViewModel.getState().setLoggedInUser(student);
        // should set it to the currently logged in user's home page
        coursesViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(coursesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        // goes back to home page
    }
}
