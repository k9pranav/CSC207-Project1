package interface_adapter.landing_page;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class LandingPageViewModel extends ViewModel {

    public final String STUDENT_BUTTON_LABEL = "Student";
    public final String ADMIN_BUTTON_LABEL = "Administrator";

    private LandingPageState state = new LandingPageState();

    public LandingPageViewModel() {
        super("Landing Page");
    }

    public void setState(LandingPageState state) {
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

    public LandingPageState getState() {
        return state;
    }

}
