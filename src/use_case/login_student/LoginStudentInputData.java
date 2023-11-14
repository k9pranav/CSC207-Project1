package use_case.login_student;

public class LoginStudentInputData {

    final private String email;

    final private String password;

    public LoginStudentInputData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    String getEmail() {return  email;}

    String getPassword() {return password;}
}


