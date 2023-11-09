package use_case.signup_admin;

public class SignupAdminOutputData {

    private final String firstName;
    private final String lastName;
    private final String email;

    private boolean useCaseFailed;

    public SignupAdminOutputData(String firstName, String lastName,
                            String email, boolean useCaseFailed) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.useCaseFailed = useCaseFailed;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }


}
