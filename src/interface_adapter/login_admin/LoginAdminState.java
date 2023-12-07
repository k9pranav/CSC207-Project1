package interface_adapter.login_admin;

public class LoginAdminState {
    private String email = "";
    private String emailError = null;
    private String password = "";
    private String passwordError = null;

    public LoginAdminState(LoginAdminState copy){
        email = copy.email;
        emailError = copy.emailError;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    public LoginAdminState(){}

    public String getEmail(){return email;}
    public String getEmailError(){ return emailError;}
    public String getPassword(){return password;}
    public String getPasswordError(){return passwordError;}
    public void setEmail(String email){this.email = email;}
    public void setEmailError(String emailError){this.emailError = emailError;}
    public void setPassword(String password){this.password = password;}
    public void setPasswordError(String passwordError){this.passwordError = passwordError;}
}
