package interface_adapter.student_landing_page;

import use_case.student_landing_page.StudentLandingPageInputBoundary;

public class StudentLandingPageController {
    private final StudentLandingPageInputBoundary studentLandingInteractor;
    public StudentLandingPageController(StudentLandingPageInputBoundary studentLandingInteractor){
        this.studentLandingInteractor = studentLandingInteractor;

    }
    public void execute(String buttonPressed){
        if ("signup".equals(buttonPressed)){
            studentLandingInteractor.execute("signup");
        }
        else if ("login".equals(buttonPressed)){
            studentLandingInteractor.execute("login");
        }

    }
}
