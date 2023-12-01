package interface_adapter.admin_course_tasks;

import entity.Admin;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_courses.AdminCoursesState;
import interface_adapter.admin_courses.AdminCoursesViewModel;
import interface_adapter.edit_course_task.EditCourseTaskState;
import interface_adapter.edit_course_task.EditCourseTaskViewModel;
import interface_adapter.student_course_tasks.StudentCourseTasksState;
import use_case.admin_course_tasks.AdminCourseTasksOutputBoundary;
import use_case.admin_course_tasks.AdminCourseTasksOutputData;
import use_case.admin_course_tasks.AdminCourseTasksPopupOutputData;

import java.text.SimpleDateFormat;

public class AdminCourseTasksPresenter implements AdminCourseTasksOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final AdminCourseTasksViewModel tasksViewModel;
    private final AdminCoursesViewModel courseViewModel;
    private final EditCourseTaskViewModel editCourseTaskViewModel;

    public AdminCourseTasksPresenter(ViewManagerModel viewManagerModel, AdminCourseTasksViewModel tasksViewModel, AdminCoursesViewModel coursesViewModel, EditCourseTaskViewModel editCourseTaskViewModel){
        this.viewManagerModel = viewManagerModel;
        this.tasksViewModel = tasksViewModel;
        this.courseViewModel = coursesViewModel;
        this.editCourseTaskViewModel = editCourseTaskViewModel;
    }

    @Override
    public void prepareExit(Admin admin){
        AdminCoursesState coursesState = courseViewModel.getState();
        this.courseViewModel.setState(coursesState);
        this.courseViewModel.getState().setAdminLoggedIn(admin);
        courseViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(courseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareEditTaskView(AdminCourseTasksOutputData outputData){
        EditCourseTaskState editCourseTaskState = editCourseTaskViewModel.getState();
        this.editCourseTaskViewModel.setState(editCourseTaskState);
        this.editCourseTaskViewModel.getState().setCurrentTask(outputData.getTask());
        editCourseTaskViewModel.getState().setLoggedIn(outputData.getLoggedIn());
        editCourseTaskViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(editCourseTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareTaskPopup(AdminCourseTasksPopupOutputData outputData) {
        AdminCourseTasksState tasksState = tasksViewModel.getState();

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

}
