package use_case.admin_course_tasks;

import entity.Admin;

public class AdminCourseTasksInputData {
    final private String buttonPressed;
    final private Admin loggedIn;

    public AdminCourseTasksInputData(String buttonPressed, Admin loggedIn){
        this.buttonPressed = buttonPressed;
        this.loggedIn = loggedIn;
    }

    public String getButtonPressed(){return buttonPressed;}
    public Admin getLoggedIn(){return loggedIn;}
}
