package app;

import entity.StudentFactory;
import entity.PersonFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_student.LoggedinStudentViewModel;
import interface_adapter.login_student.LoginStudentController;
import interface_adapter.login_student.LoginStudentPresenter;
import interface_adapter.login_student.LoginStudentViewModel;
import use_case.login_student.LoginStudentDataAccessInterface;
import use_case.login_student.LoginStudentInputBoundary;
import use_case.login_student.LoginStudentInteractor;
import use_case.login_student.LoginStudentOutputBoundary;
import view.StudentLoginView;

import javax.swing.*;
import java.io.IOException;


public class LoginStudentUseCaseFactory {

    /** Prevent instantiation. */
    private LoginStudentUseCaseFactory() {}

    public static StudentLoginView create(
            ViewManagerModel viewManagerModel,
            LoginStudentViewModel loginStudentViewModel,
            LoggedinStudentViewModel loggedInViewModel,
            LoginStudentDataAccessInterface userDataAccessObject) {

        try {
            LoginStudentController loginController = createLoginUseCase(viewManagerModel, loginStudentViewModel, loggedInViewModel, userDataAccessObject);
            return new StudentLoginView(loginStudentViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginStudentController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginStudentViewModel loginViewModel,
            LoggedinStudentViewModel loggedInViewModel,
            LoginStudentDataAccessInterface userDataAccessObject) throws IOException {
        LoginStudentOutputBoundary loginOutputBoundary = new LoginStudentPresenter(viewManagerModel, loggedInViewModel, loginViewModel);
        PersonFactory userFactory = new StudentFactory();
        LoginStudentInputBoundary loginInteractor = new LoginStudentInteractor(userDataAccessObject, loginOutputBoundary);
        return new LoginStudentController(loginInteractor);
    }
}

