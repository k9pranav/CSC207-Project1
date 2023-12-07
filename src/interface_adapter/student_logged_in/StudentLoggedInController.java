package interface_adapter.student_logged_in;

import use_case.student_landing_page.StudentLandingPageInputBoundary;
import use_case.student_logged_in.StudentLoggedInInputBoundary;

public class StudentLoggedInController {
    private final StudentLoggedInInputBoundary studentLoggedInInputInteractor;
    public StudentLoggedInController(StudentLoggedInInputBoundary studentLoggedInInteractor){
        this.studentLoggedInInputInteractor = studentLoggedInInteractor;

    }
    public void execute(String buttonPressed){
        if ("courses".equals(buttonPressed)) {
            studentLoggedInInputInteractor.execute("courses");
        } else if ("calendar".equals(buttonPressed)) {
            studentLoggedInInputInteractor.execute("calendar");

        }
    }
}
