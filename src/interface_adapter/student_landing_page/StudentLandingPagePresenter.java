package interface_adapter.student_landing_page;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup_student.SignupStudentState;
import interface_adapter.signup_student.SignupStudentViewModel;
import use_case.student_landing_page.StudentLandingPageOutputBoundary;

public class StudentLandingPagePresenter implements StudentLandingPageOutputBoundary {
    private final StudentLandingPageViewModel studentLandingPageViewModel;
    private final SignupStudentViewModel signupViewModel;
    private final LoginStudentViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public StudentLandingPagePresenter(ViewManagerModel viewManagerModel, StudentLandingPageViewModel studentLandingPageViewModel, SignupStudentViewModel signupViewModel, LoginStudentViewModel loginViewModel){
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.studentLandingPageViewModel = studentLandingPageViewModel;
    }

    @Override
    public void prepareSignupLandingPage(){
        SignupStudentState signupState = signupViewModel.getState();
        this.signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareLoginLandingPage(){
        SignupStudentState loginState = loginViewModel.getState();
        this.loginViewModel.setState(signupState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
