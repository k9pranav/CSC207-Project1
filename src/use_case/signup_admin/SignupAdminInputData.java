package use_case.signup_admin;

public class SignupAdminInputData {

    final private String firstName;
    final private String lastName;

    final private String password;
    final private String repeatPassword;

    private final String email;

    public SignupInputData(String firstName, String lastName,
                           String Password, String repeatPassword,
                           String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.Password = Password;
        this.repeatPassword = repeatPassword;
        this.email = email;
    }

    String getFirstName() {
        return firstName;
    }
    String getLastName() {
        return lastName;
    }
    String getPassword() {
        return password;
    }
    public String getRepeatPassword() {
        return repeatPassword;
    }
    String getEmail(){return email;}

}
