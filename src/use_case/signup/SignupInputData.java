package use_case.signup;

public class SignupInputData {

    final private String studentId;

    final private String password;

    final private String repeatPassword;

    public SignupInputData(String studentId, String password, String repeatPassword) {
        this.studentId = studentId;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String getStudentId(){
        return studentId;
    }

    String getPassword() {
        return password;
    }

    String getRepeatPassword() {
        return repeatPassword;
    }
}

