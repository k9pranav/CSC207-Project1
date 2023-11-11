package use_case.signup_student;

import entity.Student;
import entity.StudentFactory;
import use_case.signup_admin.SignupAdminOutputData;

public class SignupStudentInteractor implements SignupStudentInputBoundary{
    final SignupStudentDataAccessInterface userDataAccessObject;
    final SignupStudentOutputBoundary userPresenter;
    Student student;

    public SignupStudentInteractor(SignupStudentDataAccessInterface userDataAccessObject,
                                 SignupStudentOutputBoundary userPresenter,
                                 StudentFactory studentFactory){
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(SignupStudentInputData signupStudentInputData) {
        if (userDataAccessObject.existsByEmail(signupStudentInputData.getEmail())){
            userPresenter.prepareFailView("User Already Exists");
        } else if (!signupStudentInputData.getPassword().equals(signupStudentInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords Don't Match");
        } else {
            Student student = new Student(signupStudentInputData.getFirstName(),
                    signupStudentInputData.getLastName(), signupStudentInputData.getPassword(),
                    signupStudentInputData.getRepeatPassword(), signupStudentInputData.getEmail());

            userDataAccessObject.save(student);
            userDataAccessObject.createNewCalendar(student);

            SignupStudentOutputData signupStudentOutputData = new SignupStudentOutputData(student.getFirstName(),
                    student.getLastName(), student.getEmail(), false);
        }

    }
}
