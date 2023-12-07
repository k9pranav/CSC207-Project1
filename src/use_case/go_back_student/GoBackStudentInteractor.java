package use_case.go_back_student;

import entity.Admin;
import entity.Student;

public class GoBackStudentInteractor implements GoBackStudentInputBoundary {

    final GoBackStudentDataAccessInterface userDataAccessObject;

    final GoBackStudentOutputBoundary goBackPresenter;


    public GoBackStudentInteractor(GoBackStudentDataAccessInterface userDataAccessObject, GoBackStudentOutputBoundary goBackPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.goBackPresenter = goBackPresenter;
    }

    @Override
    public void execute(GoBackStudentInputData goBackStudentInputData) {
        String email = goBackStudentInputData.getEmail();
        String accountType = goBackStudentInputData.getAccountType();

        if (accountType.equals("STUDENT")){
            Student student = (Student) userDataAccessObject.get(email);

            GoBackStudentOutputData goBackOutputData = new GoBackStudentOutputData(student.getEmail(), accountType, false);
            goBackPresenter.prepareSuccessView(goBackOutputData);
        } else {
            Admin admin = (Admin) userDataAccessObject.get(email);
//TODO:
//            GoBackAdminOutputData goBackOutputData = new GoBackAdminOutputData(admin.getEmail(),accountType ,false);
//            GoBackAdminPresenter.prepareSuccessView(goBackOutputData);
        }
    }
}
