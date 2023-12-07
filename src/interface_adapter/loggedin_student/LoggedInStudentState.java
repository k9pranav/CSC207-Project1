package interface_adapter.loggedin_student;

import entity.Person;

public class LoggedInStudentState {
    private String email = "";

    private Person loggedInPerson = null;

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

    public void setLoggedInUser (Person person) {loggedInPerson = person;}
}
