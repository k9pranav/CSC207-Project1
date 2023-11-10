package interface_adapter.signup_admin;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupAdminViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Sign Up View";
    public static final String FIRSTNAME_LABEL = "First Name";
    public static final String LASTNAME_LABEL = "Last Name";

    public static final String EMAIL_LABEL = "Email ";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private SignupAdminState state = new SignupAdminState();

    public SignupAdminViewModel(){super("sign up admin");}

    public void setState(SignupAdminState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupAdminState getState() {
        return state;
    }
}
