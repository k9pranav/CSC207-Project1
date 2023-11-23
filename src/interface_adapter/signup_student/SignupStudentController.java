package interface_adapter.signup_student;

import use_case.signup_student.SignupStudentInputBoundary;
import use_case.signup_student.SignupStudentInputData;
public class SignupStudentController {
    final SignupStudentInputBoundary studentSignupUseCaseInteractor;
    public SignupStudentController(SignupStudentInputBoundary studentSignupUseCaseInteractor) {
        this.studentSignupUseCaseInteractor = studentSignupUseCaseInteractor;
    }

    public void execute(String firstName, String lastName, String password,
                        String repeatPassword, String email) {

        SignupStudentInputData signupStudentInputData = new SignupStudentInputData(firstName,
                lastName, password, repeatPassword, email);

        studentSignupUseCaseInteractor.execute(signupStudentInputData);

    }
}
