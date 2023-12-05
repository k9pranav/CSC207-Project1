package interface_adapter.login_student;

import interface_adapter.landing_page.LandingPageState;
import interface_adapter.landing_page.LandingPageViewModel;
import interface_adapter.student_logged_in.StudentLoggedInState;
import interface_adapter.student_logged_in.StudentLoggedInViewModel;
import use_case.login_student.LoginStudentOutputData;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_student.LoggedInStudentState;
import use_case.login_student.LoginStudentOutputBoundary;

public class LoginStudentPresenter implements LoginStudentOutputBoundary {
    private final LoginStudentViewModel loginStudentViewModel;
    private final StudentLoggedInViewModel studentLoggedInViewModel;
    private ViewManagerModel viewManagerModel;
    private final LandingPageViewModel landingViewModel;


    public LoginStudentPresenter(ViewManagerModel viewManagerModel,
                                 StudentLoggedInViewModel studentLoggedInViewModel,
                                 LandingPageViewModel landingViewModel,
                                 LoginStudentViewModel loginStudentViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.studentLoggedInViewModel = studentLoggedInViewModel;
        this.loginStudentViewModel = loginStudentViewModel;
        this.landingViewModel = landingViewModel;

    }

    @Override
    public void prepareSuccessView(LoginStudentOutputData response) {
        // On success, switch to the logged in view.

        StudentLoggedInState studentLoggedInState = studentLoggedInViewModel.getState();
        studentLoggedInState.setEmail(response.getEmail());
        this.studentLoggedInViewModel.setState(studentLoggedInState);
        this.studentLoggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(studentLoggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginStudentState loginState = loginStudentViewModel.getState();
        loginState.setEmailError(error);
        loginStudentViewModel.firePropertyChanged();
    }
    public void prepareCancelView(){
        LandingPageState landingState = landingViewModel.getState();
        this.landingViewModel.setState(landingState);
        landingViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(landingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
