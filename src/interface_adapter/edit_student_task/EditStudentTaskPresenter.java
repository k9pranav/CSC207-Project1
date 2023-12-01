package interface_adapter.edit_student_task;

import interface_adapter.ViewManagerModel;
import interface_adapter.edit_course_task.EditCourseTaskViewModel;
import interface_adapter.student_logged_in.StudentLoggedInState;
import interface_adapter.student_logged_in.StudentLoggedInViewModel;
import use_case.edit_student_task.EditStudentTaskDataInputData;
import use_case.edit_student_task.EditStudentTaskOutputBoundary;

public class EditStudentTaskPresenter implements EditStudentTaskOutputBoundary {

    private final EditStudentTaskViewModel editStudentTaskViewModelViewModel;

    private final StudentLoggedInViewModel studentLoggedInViewModel;

    private final ViewManagerModel viewManagerModel;


    public EditStudentTaskPresenter(EditStudentTaskViewModel editStudentTaskViewModelViewModel,
                                    StudentLoggedInViewModel studentLoggedInViewModel,
                                    ViewManagerModel viewManagerModel) {
        this.editStudentTaskViewModelViewModel = editStudentTaskViewModelViewModel;
        this.studentLoggedInViewModel = studentLoggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(EditStudentTaskDataInputData editStudentTaskDataInputData) {
        //On success, go to student logged in

        StudentLoggedInState studentLoggedInState = studentLoggedInViewModel.getState();
        this.studentLoggedInViewModel.setState(studentLoggedInState);
        this.studentLoggedInViewModel.firePropertyChanged();



    }

    @Override
    public void prepareFailView(String errorString) {
        EditStudentTaskState editStudentTaskState = editStudentTaskViewModelViewModel.getState();
        editStudentTaskState.setError(errorString);
        editStudentTaskViewModelViewModel.firePropertyChanged();

    }
}
