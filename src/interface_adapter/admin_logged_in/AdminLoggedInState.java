package interface_adapter.admin_logged_in;

import entity.Admin;

public class AdminLoggedInState {
    private Admin loggedIn;

    public AdminLoggedInState(AdminLoggedInState copy){copy.loggedIn = loggedIn;}

    public AdminLoggedInState(){}

    public void setLoggedInUser(Admin admin){this.loggedIn = admin;}

}
