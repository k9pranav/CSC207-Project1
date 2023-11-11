package use_case.login_admin;

public class LoginAdminInputData {

    final private String email;

    final private String password;


    public LoginAdminInputData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    String getEmail() {return email;}

    String getPassword() {return password;}
}
