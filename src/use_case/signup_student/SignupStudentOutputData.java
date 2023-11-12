package use_case.signup_student;

public class SignupStudentOutputData {

    final private String first_name;
    final private String last_name;
    private final String email;
    final private String password;
    final private String repeatPassword;

    //TODO -> change vars to vars needed
    public SignupStudentOutputData(String first_name, String last_name, String email, String password, String repeatPassword) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }


    String getFirstName() {
        return first_name;
    }

    String getLastName() {
        return last_name;
    }

    String getEmail() {
        return email;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}
