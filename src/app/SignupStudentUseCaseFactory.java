package app;

import entity.StudentFactory;
import entity.PersonFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login_student.LoginStudentViewModel;
import interface_adapter.signup_student.SignupStudentController;
import interface_adapter.signup_student.SignupStudentPresenter;
import interface_adapter.signup_student.SignupStudentViewModel;
import use_case.signup_student.SignupStudentDataAccessInterface;
import use_case.signup_student.SignupStudentInputBoundary;
import use_case.signup_student.SignupStudentInteractor;
import use_case.signup_student.SignupStudentOutputBoundary;
import view.StudentSignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupStudentUseCaseFactory {
    public static StudentSignupView create(
            ViewManagerModel viewManagerModel, LoginStudentViewModel loginViewModel, SignupStudentViewModel signupViewModel,
            SignupStudentDataAccessInterface userDataAccessObject) {
        try {
            SignupStudentController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel,
                    loginViewModel, userDataAccessObject);
            return new StudentSignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static SignupStudentController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupStudentViewModel signupViewModel, LoginStudentViewModel loginViewModel, SignupStudentDataAccessInterface userDataAccessObject) throws IOException {
        SignupStudentOutputBoundary signupOutputBoundary = new SignupStudentPresenter(viewManagerModel, signupViewModel, loginViewModel);
        PersonFactory userFactory = new StudentFactory();
        SignupStudentInputBoundary userSignupInteractor = new SignupStudentInteractor(userDataAccessObject, signupOutputBoundary, (StudentFactory) userFactory);
        return new SignupStudentController(userSignupInteractor);
    }
}
