package interface_adapter.signup_admin;

import use_case.signup_admin.SignupAdminInputBoundary;
import use_case.signup_admin.SignupAdminInputData;

public class SignupAdminController {

    final SignupAdminInputBoundary signupAdminInputBoundary;

    public SignupAdminController(SignupAdminInputBoundary signupAdminInputBoundary){
        this.signupAdminInputBoundary = signupAdminInputBoundary;
    }

    public void execute(String firstName, String lastName, String password,
                        String repeatPassword, String email) {

        SignupAdminInputData signupAdminInputData = new SignupAdminInputData(firstName,
                lastName, password, repeatPassword, email);

        signupAdminInputBoundary.execute(signupAdminInputData);
    }


}
