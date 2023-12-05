package interface_adapter.student_logged_in;

import interface_adapter.signup_student.SignupStudentState;

public class StudentLoggedInState {
    private String email = "";
    private String firstName = "";
    private String lastName = "";

    public StudentLoggedInState(StudentLoggedInState copy){
        firstName = copy.firstName;
        lastName = copy.lastName;
        email = copy.email;
    }
    public StudentLoggedInState(){}
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setEmail(String email){this.email = email;}

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getEmail(){return email;}

    public String toString() {
        return "Logged In State{" +
                "firstname='" + firstName + '\'' +
                "lastname='" + lastName + '\'' +
                "email='" + email + '\'' +
                '}';
    }
}
