package interface_adapter.admin_logged_in;

public class AdminLoggedInViewModel {
    public AdminLoggedInState getState() {return new AdminLoggedInState();}

    public void setState(AdminLoggedInState loggedInState) {
    }

    public void firePropertyChanged() {
    }

    public String getViewName() {return "";
    }
}