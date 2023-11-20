package interface_adapter.login_student;

import use_case.login_student.LoginStudentOutputData;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_student.LoggedInStudentState;
import interface_adapter.loggedin_student.LoggedinStudentViewModel;
import use_case.login_student.LoginStudentOutputBoundary;

public class LoginStudentPresenter implements LoginStudentOutputBoundary {
    private final LoginStudentViewModel loginStudentViewModel;
    private final LoggedinStudentViewModel loggedInStudentViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginStudentPresenter(ViewManagerModel viewManagerModel,
                                 LoggedinStudentViewModel loggedInStudentViewModel,
                          LoginStudentViewModel loginStudentViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInStudentViewModel = loggedInStudentViewModel;
        this.loginStudentViewModel = loginStudentViewModel;
    }

    @Override
    public void prepareSuccessView(LoginStudentOutputData response) {
        // On success, switch to the logged in view.

        LoggedInStudentState loggedInStudentState = loggedInStudentViewModel.getState();
        loggedInStudentState.setEmail(response.getEmail());
        this.loggedInStudentViewModel.setState(loggedInStudentState);
        this.loggedInStudentViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInStudentViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginStudentState loginState = loginStudentViewModel.getState();
        loginState.setEmailError(error);
        loginStudentViewModel.firePropertyChanged();
    }
}
}
