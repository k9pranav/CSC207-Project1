package interface_adapter.loggedin_student;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedinStudentViewModel extends ViewModel {
    public final String TITLE_LABEL = "Logged In View";

    private LoggedInStudentState student_state = new LoggedInStudentState();

    public static final String LOGOUT_BUTTON_LABEL = "Log out";
    private String loggedInUser;

    public LoggedinStudentViewModel() {
        super("logged in student");
    }

    public void setState(LoggedInStudentState state) {
        this.student_state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("student state", null, this.student_state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoggedInStudentState getState() {
        return student_state;
    }


    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
