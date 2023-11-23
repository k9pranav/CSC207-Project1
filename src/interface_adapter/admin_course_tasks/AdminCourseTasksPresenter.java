package interface_adapter.admin_course_tasks;

import entity.Admin;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_courses.AdminCoursesState;
import interface_adapter.admin_courses.AdminCoursesViewModel;
import interface_adapter.edit_course_task.EditCourseTaskState;
import interface_adapter.edit_course_task.EditCourseTaskViewModel;
import use_case.admin_course_tasks.AdminCourseTasksOutputBoundary;
import use_case.admin_course_tasks.AdminCourseTasksOutputData;

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

}
