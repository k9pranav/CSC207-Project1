package interface_adapter.edit_course_task;

import interface_adapter.ViewManagerModel;
import interface_adapter.admin_tasks.AdminTasksState;
import interface_adapter.admin_tasks.AdminTasksViewModel;
import use_case.edit_course_task.EditCourseTaskOutputBoundary;
import use_case.edit_course_task.EditCourseTaskOutputData;
import view.ViewManager;

public class EditCourseTaskPresenter  implements EditCourseTaskOutputBoundary {

    private final EditCourseTaskViewModel editCourseTaskViewModel;

    private final AdminTasksViewModel adminTasksViewModel;
    private ViewManagerModel viewManagerModel;

    public EditCourseTaskPresenter(EditCourseTaskViewModel editCourseTaskViewModel,
                                   AdminTasksViewModel adminTasksViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.editCourseTaskViewModel = editCourseTaskViewModel;
        this.adminTasksViewModel = adminTasksViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(EditCourseTaskOutputData courseTask) {
        //On success, go to admin tasks in view

        AdminTasksState adminTasksState = adminTasksViewModel.getState();
        this.adminTasksViewModel.setState(adminTasksState);
        this.adminTasksViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(adminTasksViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();


    }

    @Override
    public void prepareFailView(String errorstring) {

        EditCourseTaskState editCourseTaskState = editCourseTaskViewModel.getState();
        editCourseTaskState.setError(errorstring);
        editCourseTaskViewModel.firePropertyChanged();

    }
}
