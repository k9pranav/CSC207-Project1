package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_student.LoginStudentViewModel;
import interface_adapter.signup_student.SignupStudentViewModel;
import interface_adapter.student_landing_page.StudentLandingPageController;
import interface_adapter.student_landing_page.StudentLandingPagePresenter;
import interface_adapter.student_landing_page.StudentLandingPageViewModel;
import use_case.student_landing_page.StudentLandingPageInputBoundary;
import use_case.student_landing_page.StudentLandingPageOutputBoundary;
import use_case.student_landing_page.StudentLandingPageUseCaseInteractor;
import view.StudentLandingPageView;

public class StudentLandingPageUseCaseFactory {
    public static StudentLandingPageView create(ViewManagerModel viewManagerModel, StudentLandingPageViewModel studentLandingPageViewModel, SignupStudentViewModel studentSignupViewModel, LoginStudentViewModel studentLoginViewModel) {
        StudentLandingPageController studentLandingPageController = createStudentLandingPageUseCase(viewManagerModel, studentLandingPageViewModel, studentLoginViewModel, studentSignupViewModel);
        return new StudentLandingPageView(studentLandingPageController, studentLandingPageViewModel);
    }

    private static StudentLandingPageController createStudentLandingPageUseCase(ViewManagerModel viewManagerModel, StudentLandingPageViewModel studentLandingPageViewModel, LoginStudentViewModel loginStudentViewModel, SignupStudentViewModel signupStudentViewModel) {
        StudentLandingPageOutputBoundary studentLandingPageOutputBoundary = new StudentLandingPagePresenter(viewManagerModel, studentLandingPageViewModel, signupStudentViewModel, loginStudentViewModel);

        StudentLandingPageInputBoundary landingPageInteractor = new StudentLandingPageUseCaseInteractor(studentLandingPageOutputBoundary);
        return new StudentLandingPageController(landingPageInteractor);

    }
}

