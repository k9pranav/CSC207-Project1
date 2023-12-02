package interface_adapter.signup_student;

import interface_adapter.login_student.LoginStudentState;
import interface_adapter.login_student.LoginStudentViewModel;
import interface_adapter.ViewManagerModel;
import use_case.signup_student.SignupStudentOutputBoundary;
import use_case.signup_student.SignupStudentOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignupStudentPresenter implements SignupStudentOutputBoundary{
    private final SignupStudentViewModel signupStudentViewModel;
    private final LoginStudentViewModel loginStudentViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupStudentPresenter(ViewManagerModel viewManagerModel,
                           SignupStudentViewModel signupStudentViewModel,
                           LoginStudentViewModel loginStudentViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupStudentViewModel = signupStudentViewModel;
        this.loginStudentViewModel = loginStudentViewModel;
    }

    @Override
    public void prepareSuccessView(SignupStudentOutputData response) {

        LoginStudentState loginStudentState = loginStudentViewModel.getState();
        loginStudentState.setEmail(response.getEmail());
        this.loginStudentViewModel.setState(loginStudentState);
        loginStudentViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginStudentViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SignupStudentState signupState = signupStudentViewModel.getState();
        signupState.setEmailError(error);
        signupStudentViewModel.firePropertyChanged();
    }

}
