package interface_adapter.go_back_student;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_student.LoggedInStudentState;
import interface_adapter.loggedin_student.LoggedinStudentViewModel;
import use_case.go_back_student.GoBackStudentOutputBoundary;
import use_case.go_back_student.GoBackStudentOutputData;

public class GoBackStudentPresenter implements GoBackStudentOutputBoundary {

    private final GoBackStudentSViewModel goBackViewModel;

    private final LoggedinStudentViewModel loggedInViewModel;

    private ViewManagerModel viewManagerModel;

    public GoBackStudentPresenter(GoBackStudentSViewModel goBackViewModel, ViewManagerModel viewManagerModel, LoggedinStudentViewModel loggedInViewModel) {
        this.goBackViewModel = goBackViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(GoBackStudentOutputData response) {
        LoggedInStudentState loggedInState = loggedInViewModel.getState();
        loggedInState.setEmail(response.getEmail());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
