package interface_adapter.go_back_student;

public class GoBackStudentState {

    private String email = "";

    private String accountType = null;

    public GoBackStudentState(GoBackStudentState copy){
        this.accountType = copy.accountType;
        this.email = copy.accountType;
    };

    public GoBackStudentState(){}

    public String getAccountType() {
        return accountType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
