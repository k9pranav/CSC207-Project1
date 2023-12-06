package interface_adapter.go_back_student;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GoBackStudentSViewModel extends ViewModel {

    private GoBackStudentState state = new GoBackStudentState();

    public static final String GO_BACK_BUTTON_LABEL = "Go Back";

    public GoBackStudentSViewModel() {
        super("go back");
    }

    public void setState(GoBackStudentState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("goBackState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
    public GoBackStudentState getState() {
        return state;
    }

}
