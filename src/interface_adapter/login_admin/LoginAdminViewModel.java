package interface_adapter.login_admin;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class LoginAdminViewModel extends ViewModel {
    private LoginAdminState state = new LoginAdminState();

    public LoginAdminState getState() {
        return state;
    }
    public void setState(LoginAdminState s){}

    public void firePropertyChanged() {
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public String getViewName() {return "";
    }
}
