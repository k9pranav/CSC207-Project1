package use_case.signup_admin;

public class SignupAdminInputData {

    private final String firstName;
    private final String lastName;

    private final String password;
    private final String repeatPassword;

    private final String email;

    public SignupAdminInputData(String firstName, String lastName,
                                String password, String repeatPassword,
                                String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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
