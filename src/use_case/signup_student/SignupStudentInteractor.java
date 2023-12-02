package use_case.signup_student;

import entity.Student;
import entity.StudentFactory;

import java.io.IOException;

public class SignupStudentInteractor implements SignupStudentInputBoundary {
    final SignupStudentDataAccessInterface studentDataAccessObject;
    final SignupStudentOutputBoundary studentPresenter;
    final StudentFactory studentFactory;


    public SignupStudentInteractor(SignupStudentDataAccessInterface studentDataAccessObject, SignupStudentOutputBoundary studentPresenter, StudentFactory studentFactory) {
        this.studentDataAccessObject = studentDataAccessObject;
        this.studentPresenter = studentPresenter;
        this.studentFactory = studentFactory;
    }

    @Override
    public void execute(SignupStudentInputData signupStudentInputData) {
        if (studentDataAccessObject.existsByEmail(signupStudentInputData.getEmail())) {
            studentPresenter.prepareFailView("User already exists.");
        } else if (!signupStudentInputData.getPassword().equals(signupStudentInputData.getRepeatPassword())) {
            studentPresenter.prepareFailView("Passwords don't match.");
        } else {
            try {
                Student student = (Student) studentFactory.create(signupStudentInputData.getFirstName(), signupStudentInputData.getLastName(), signupStudentInputData.getPassword(), signupStudentInputData.getRepeatPassword(), signupStudentInputData.getEmail());
                studentDataAccessObject.save(student);
                SignupStudentOutputData signupStudentOutputData = new SignupStudentOutputData(student.getFirstName(), student.getLastName(), student.getEmail(), student.getPassword(), student.getRepeatPassword());
                studentPresenter.prepareSuccessView(signupStudentOutputData);
            }
            catch(IOException ex){
                System.out.println("Error in user");
            }
        }
    }
}

