package interface_adapter.login_student;

import use_case.login_student.LoginStudentInputData;
import use_case.login_student.LoginStudentInputBoundary;
import use_case.signup_student.SignupStudentInputBoundary;
import use_case.signup_student.SignupStudentInputData;
public class LoginStudentController {
    final LoginStudentInputBoundary loginStudentUseCaseInteractor;

    public LoginStudentController(LoginStudentInputBoundary loginStudentUseCaseInteractor) {
        this.loginStudentUseCaseInteractor = loginStudentUseCaseInteractor;
    }


    public void execute(String email, String password) {
        LoginStudentInputData loginStudentInputData = new LoginStudentInputData(
                email, password);

        loginStudentUseCaseInteractor.execute(loginStudentInputData);
    }
}