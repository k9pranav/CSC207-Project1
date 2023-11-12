package use_case.signup_student;

import entity.Student;
import entity.StudentFactory;

import java.time.LocalDateTime;

public class SignupStudentInteractor implements SignupStudentInputBoundary{
    final SignupStudentDataAccessInterface studentDataAccessObject;
    final SignupStudentOutputBoundary studentPresenter;
    final entity.StudentFactory studentFactory;

    public SignupStudentInteractor(SignupStudentDataAccessInterface signupstudentDataAccessInterface,
                                   SignupStudentOutputBoundary signupOutputBoundary,
                                   StudentFactory studentFactory) {
        this.studentDataAccessObject = signupstudentDataAccessInterface;
        this.studentPresenter = signupOutputBoundary;
        this.studentFactory = studentFactory;
    }
    @Override
    public void execute(SignupStudentInputData signupStudentInputData) {
        if (studentDataAccessObject.existsByEmail(signupStudentInputData.getEmail())) {
            studentPresenter.prepareFailView("User already exists.");
        } else if (!signupStudentInputData.getPassword().equals(signupStudentInputData.getRepeatPassword())) {
            studentPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            Student student = (Student) studentFactory.create(signupStudentInputData.getEmail(), signupStudentInputData.getPassword(), now);
            studentDataAccessObject.save(student);

            SignupStudentOutputData signupStudentOutputData = new SignupStudentOutputData(student.getFirstName(), now.toString(), false);
            studentPresenter.prepareSuccessView(signupStudentOutputData);
        }
    }
}
