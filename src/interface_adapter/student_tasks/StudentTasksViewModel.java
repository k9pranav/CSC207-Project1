package interface_adapter.student_tasks;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StudentTasksViewModel extends ViewModel {

    public final String NEW_STUDENT_TASK_LABEL = "Create New Task";
    private StudentTasksState state = new StudentTasksState();
    public StudentTasksViewModel(){
        super("student tasks page");
    }

    public void setState(StudentTasksState state){
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}

    public void firePropertyChangedPopup(){support.firePropertyChange("popup", null, this.state);}
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public StudentTasksState getState() {
        return state;
    }
}
