package use_case.go_back_student;

public class GoBackStudentOutputData {

    private final String email;

    private final String accountType;

    private boolean useCaseFailed;

    public GoBackStudentOutputData(String email, String accountType, boolean useCaseFailed) {
        this.email = email;
        this.accountType = accountType;
        this.useCaseFailed = useCaseFailed;
    }

    public String getAccountType() {
        return accountType;
    }


    public String getEmail() {
        return email;
    }
}

