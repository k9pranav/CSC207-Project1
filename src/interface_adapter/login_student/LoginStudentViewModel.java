package interface_adapter.login_student;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginStudentViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Login Up View";
    public static final String FIRSTNAME_LABEL = "First Name";
    public static final String LASTNAME_LABEL = "Last Name";

    public static final String EMAIL_LABEL = "Email ";
    public static final String PASSWORD_LABEL = "Enter password";

    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private LoginStudentState student_state = new LoginStudentState();

    public LoginStudentViewModel() {
        super("log in student");
    }

    public void setState(LoginStudentState state) {
        this.student_state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.student_state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoginStudentState getState() {
        return student_state;
    }
}
