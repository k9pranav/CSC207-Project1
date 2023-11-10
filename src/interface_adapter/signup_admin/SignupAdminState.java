package interface_adapter.signup_admin;

public class SignupAdminState {

    private String firstName = "";
    private String firstNameError = null;
    private String lastName = "";
    private String lastNameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
    private String email = "";
    private String emailError = null;

    public SignupAdminState(SignupAdminState copy){
        firstName = copy.firstName;
        firstNameError = copy.firstNameError;

        lastName = copy.lastName;
        lastNameError = copy.lastNameError;

        password = copy.password;
        passwordError = copy.passwordError;

        repeatPassword = copy.repeatPassword;
        repeatPasswordError = copy.repeatPasswordError;

        email = copy.email;
        emailError = copy.emailError;
    }

    public SignupAdminState(){}

    public String getFirstName(){return firstName;}
    public String getFirstNameError(){return firstNameError;}
    public String getLastName(){return lastName;}
    public String getLastNameError(){return lastNameError;}
    public String getPassword(){return password;}
    public String getPasswordError(){return passwordError;}
    public String getRepeatPassword(){return repeatPassword;}
    public String getRepeatPasswordError(){return repeatPasswordError;}
    public String getEmail(){return email;}
    public String getEmailError() {return emailError;}


    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setFirstNameError(String firstNameError){this.firstNameError = firstNameError;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setLastNameError(String lastNameError){this.lastNameError = lastNameError;}
    public void setPassword(String password){this.password = password;}
    public void setPasswordError(String passwordError){this.passwordError
            = passwordError;}
    public void setRepeatPassword(String repeatPassword){this.repeatPassword = repeatPassword;}
    public void setRepeatPasswordError(String repeatPasswordError){this.repeatPasswordError
            = repeatPasswordError;}
    public void getEmail(String email){this.email = email;}
    public void getEmailError(String emailError) {this.emailError = emailError;}

    public String toString() {
        return "SignupState{" +
                "firstname='" + firstName + '\'' +
                "lastname='" + lastName + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }



}
