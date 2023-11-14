package use_case.login_student;

public class LoginStudentOutputData {

    private final String email;

    private boolean useCaseFailed;


    public LoginStudentOutputData(String email, boolean useCaseFailed) {
        this.email = email;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {return email;}
}
