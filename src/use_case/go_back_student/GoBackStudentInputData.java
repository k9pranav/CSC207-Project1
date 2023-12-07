package use_case.go_back_student;

public class GoBackStudentInputData {

    final private String email;

    final private String accountType;


    public GoBackStudentInputData(String email, String accountType) {
        this.email = email;
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getEmail() {
        return email;
    }
}
