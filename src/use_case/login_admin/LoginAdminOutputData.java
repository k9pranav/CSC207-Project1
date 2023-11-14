package use_case.login_admin;

public class LoginAdminOutputData {

    private final String email;

    private boolean useCaseFailed;


    public LoginAdminOutputData(String email, boolean useCaseFailed) {
        this.email = email;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {return  email;}
}
