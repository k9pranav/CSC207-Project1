package interface_adapter.admin_landing_page;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AdminLandingPageViewModel extends ViewModel {
    public final String TITLE_LABEL = "Administrator Portal";

    public final String SIGNUP_BUTTON_LABEL = "Sign up";
    public final String LOGIN_BUTTON_LABEL = "Login";

    private AdminLandingPageState state = new AdminLandingPageState();

    public AdminLandingPageViewModel() {
        super("admin landing page");
    }

    public void setState(AdminLandingPageState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AdminLandingPageState getState() {
        return state;
    }
}
