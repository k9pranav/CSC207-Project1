package use_case.signup;

public class SignupInputData {

    final private String name;

    final private String password;

    final private String repeatPassword;
    private final String email;

    public SignupInputData(String name, String password, String repeatPassword, String email) {
        this.name = name;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
    }

    String getName(){
        return name;
    }

    String getPassword() {
        return password;
    }

    String getRepeatPassword() {
        return repeatPassword;
    }
}

