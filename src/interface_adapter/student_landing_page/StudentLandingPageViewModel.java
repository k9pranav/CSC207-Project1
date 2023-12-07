package interface_adapter.student_landing_page;

import interface_adapter.ViewModel;
import interface_adapter.admin_landing_page.AdminLandingPageState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StudentLandingPageViewModel extends ViewModel {
    public final String TITLE_LABEL = "Student Portal";

    public final String SIGNUP_BUTTON_LABEL = "Sign up";
    public final String LOGIN_BUTTON_LABEL = "Login";

    private StudentLandingPageState state = new StudentLandingPageState();

    public StudentLandingPageViewModel() {

        super("student landing page");
    }

    public void setState(StudentLandingPageState state) {
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

    public StudentLandingPageState getState() {
        return state;
    }
}
