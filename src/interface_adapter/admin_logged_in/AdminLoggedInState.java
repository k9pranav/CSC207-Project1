package interface_adapter.admin_logged_in;

public class AdminLoggedInState {
    private String email = "";
    private String firstName = "";
    private String lastName = "";

    public AdminLoggedInState(AdminLoggedInState copy){
        firstName = copy.firstName;
        lastName = copy.lastName;
        email = copy.email;
    }
    public AdminLoggedInState(){}
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
