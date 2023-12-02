package use_case.signup_admin;

import entity.Admin;
import entity.AdminFactory;
import entity.Person;

import java.io.IOException;

public class SignupAdminInteractor implements SignupAdminInputBoundary {

    final SignupAdminDataAccessInterface userDataAccessObject;
    final SignupAdminOutputBoundary userPresenter;
    Admin admin;

    public SignupAdminInteractor(SignupAdminDataAccessInterface userDataAccessObject,
                                 SignupAdminOutputBoundary userPresenter,
                                 AdminFactory adminFactory){
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.admin = admin;
    }

    @Override
    public void execute(SignupAdminInputData signupAdminInputData) {
        if (userDataAccessObject.existsByEmail(signupAdminInputData.getEmail())){
            userPresenter.prepareFailView("User Already Exists");
        } else if (!signupAdminInputData.getPassword().equals(signupAdminInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords Don't Match");
        } else {
            try {
            Admin admin = new Admin(signupAdminInputData.getFirstName(),
                    signupAdminInputData.getLastName(), signupAdminInputData.getPassword(),
                    signupAdminInputData.getRepeatPassword(), signupAdminInputData.getEmail());

            //userDataAccessObject.createCalendar(admin); TODO: Im unsure how to fix this have not been handling calendar
            userDataAccessObject.save(admin);


            SignupAdminOutputData signupAdminOutputData = new SignupAdminOutputData(admin.getFirstName(),
                    admin.getLastName(), admin.getEmail(), false);

            }
            catch(IOException ex){
                System.out.println("Error in user");
            }
        }

    }
}


/*
try {
                Student student = (Student) studentFactory.create(signupStudentInputData.getFirstName(), signupStudentInputData.getLastName(), signupStudentInputData.getPassword(), signupStudentInputData.getRepeatPassword(), signupStudentInputData.getEmail());
                studentDataAccessObject.save(student);
                SignupStudentOutputData signupStudentOutputData = new SignupStudentOutputData(student.getFirstName(), student.getLastName(), student.getEmail(), student.getPassword(), student.getRepeatPassword());
                studentPresenter.prepareSuccessView(signupStudentOutputData);
            }
            catch(IOException ex){
                System.out.println("Error in user");
            }
 */
