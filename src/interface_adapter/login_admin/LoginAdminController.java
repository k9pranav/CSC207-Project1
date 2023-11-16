package interface_adapter.login_admin;

import use_case.login_admin.LoginAdminInputBoundary;
import use_case.login_admin.LoginAdminInputData;

public class LoginAdminController {
    private final LoginAdminInputBoundary interactor;

    public LoginAdminController(LoginAdminInputBoundary interactor){
        this.interactor = interactor;
    }
    public void execute(String buttonPressed, String username, String password){
        if ("login".equals(buttonPressed)){
            LoginAdminInputData inputData = new LoginAdminInputData(username, password);
            interactor.execute(inputData);
        }
        else if ("cancel".equals(buttonPressed)){
            interactor.executeCancel();
        }
    }
}
