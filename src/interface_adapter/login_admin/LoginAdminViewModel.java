package interface_adapter.login_admin;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginAdminViewModel extends ViewModel {

    public final String TITLE_LABEL = "Admin Login View";
    public final String USERNAME_LABEL = "Enter username";
    public final String PASSWORD_LABEL = "Enter password";

    public final String LOGIN_BUTTON_LABEL = "Log in";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private LoginAdminState state = new LoginAdminState();

    public LoginAdminViewModel(){super("admin log in");}

    public LoginAdminState getState() {
        return state;
    }
    public void setState(LoginAdminState s){this.state = s;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
