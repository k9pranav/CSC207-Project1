package interface_adapter.signup_student;

import interface_adapter.ViewModel;
import interface_adapter.signup_admin.SignupAdminState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupStudentViewModel extends ViewModel{
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String FIRSTNAME_LABEL = "First Name";
    public static final String LASTNAME_LABEL = "Last Name";

    public static final String EMAIL_LABEL = "Email ";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";

    private SignupStudentState student_state = new SignupStudentState();

    public SignupStudentViewModel(){super("sign up student");}

    public void setState(SignupStudentState state) {
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

    public SignupStudentState getState() {
        return student_state;
    }
}
