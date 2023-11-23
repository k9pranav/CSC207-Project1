package use_case.admin_courses;

import entity.Admin;

public class AdminCoursesInputData {
    final private String buttonPressed;
    final private Admin loggedIn;
    public AdminCoursesInputData(String buttonPressed, Admin loggedIn){
        this.buttonPressed = buttonPressed;
        this.loggedIn = loggedIn;
    }


    public String getButton(){return buttonPressed;}
    public Admin getLoggedIn(){return loggedIn;}
}
