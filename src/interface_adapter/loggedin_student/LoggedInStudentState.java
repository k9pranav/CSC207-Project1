package interface_adapter.loggedin_student;

public class LoggedInStudentState {
    private String email = "";

    public LoggedInStudentState(LoggedInStudentState copy) {
        email = copy.email;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInStudentState() {}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
