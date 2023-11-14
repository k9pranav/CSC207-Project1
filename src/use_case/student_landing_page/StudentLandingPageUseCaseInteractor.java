package use_case.student_landing_page;

import use_case.admin_landing_page.AdminLandingPageOutputBoundary;

public class StudentLandingPageUseCaseInteractor implements StudentLandingPageInputBoundary{
    final StudentLandingPageOutputBoundary studentLandingPresenter;

    public StudentLandingPageUseCaseInteractor(StudentLandingPageOutputBoundary studentLandingPresenter){
        this.studentLandingPresenter = studentLandingPresenter;
    }

    public void execute(String buttonPressed){
        if ("signup".equals(buttonPressed)){
            studentLandingPresenter.prepareSignupLandingPage();
        }
        else if ("login".equals(buttonPressed)){
            studentLandingPresenter.prepareLoginLandingPage();
        }
    }
}
