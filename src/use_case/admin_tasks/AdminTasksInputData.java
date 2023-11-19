package use_case.admin_tasks;

import entity.Admin;

public class AdminTasksInputData {
    final private String buttonPressed;
    final private Admin loggedIn;

    public AdminTasksInputData(String buttonPressed, Admin loggedIn){
        this.buttonPressed = buttonPressed;
        this.loggedIn = loggedIn;
    }
    public String getButtonPressed(){return buttonPressed;}
    public Admin getLoggedIn(){return loggedIn;}
}
