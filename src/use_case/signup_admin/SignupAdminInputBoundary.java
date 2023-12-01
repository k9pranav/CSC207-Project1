package use_case.signup_admin;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface SignupAdminInputBoundary {
    void execute (SignupAdminInputData signupInputData) throws GeneralSecurityException, IOException;
}
