package interface_adapter.login_student;

import use_case.login_student.LoginStudentInputData;
import use_case.login_student.LoginStudentInputBoundary;

public class LoginStudentController {
    final LoginStudentInputBoundary loginStudentUseCaseInteractor;

    public LoginStudentController(LoginStudentInputBoundary loginStudentUseCaseInteractor) {
        this.loginStudentUseCaseInteractor = loginStudentUseCaseInteractor;
    }


    public void execute(String buttonPressed, String email, String password) {
        if ("login".equals(buttonPressed)){
            LoginStudentInputData loginStudentInputData = new LoginStudentInputData(
                    email, password);
            loginStudentUseCaseInteractor.execute(loginStudentInputData);
        }
        else if ("cancel".equals(buttonPressed)){
            loginStudentUseCaseInteractor.executeCancel();
        }
    }
}