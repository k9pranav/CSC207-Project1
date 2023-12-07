package interface_adapter.student_logged_in;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StudentLoggedInViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Logged In view";
    public static final String FIRSTNAME_LABEL = "First Name";
    public static final String LASTNAME_LABEL = "Last Name";

    public static final String EMAIL_LABEL = "Email ";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String COURSES_BUTTON_LABEL = "Courses";

    private StudentLoggedInState student_state = new StudentLoggedInState();

    public StudentLoggedInViewModel() {
        super("logged in student");
    }
    public void setState(StudentLoggedInState state) {
        this.student_state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("student state", null, this.student_state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public StudentLoggedInState getState() {
        return this.student_state;
    }
}
