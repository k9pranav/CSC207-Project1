package use_case.login_student;

import entity.Student;

public class LoginStudentInteractor implements LoginStudentInputBoundary{

    final LoginStudentDataAccessInterface studentDataAccessObject;

    final LoginStudentOutputBoundary loginStudentPresenter;


    public LoginStudentInteractor(LoginStudentDataAccessInterface studentDataAccessObject, LoginStudentOutputBoundary loginStudentPresenter) {
        this.studentDataAccessObject = studentDataAccessObject;
        this.loginStudentPresenter = loginStudentPresenter;
    }

    @Override
    public void execute(LoginStudentInputData loginStudentInputData) {
        String email = loginStudentInputData.getEmail();
        String password = loginStudentInputData.getPassword();

        if (!studentDataAccessObject.existsByEmail(email)) {
            loginStudentPresenter.prepareFailView(email + "Account does not exist");
        } else {
            Student student = studentDataAccessObject.get(loginStudentInputData.getEmail());

            LoginStudentOutputData loginStudentOutputData = new LoginStudentOutputData(student.getEmail(), false);
            loginStudentPresenter.prepareSuccessView(loginStudentOutputData);
        }
    }
}
