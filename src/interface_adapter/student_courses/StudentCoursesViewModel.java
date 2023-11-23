package interface_adapter.student_courses;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StudentCoursesViewModel extends ViewModel {
    public final String GET_AVERAGE_LABEL = "Calculate Grade Average";
    public final String GET_TASKS_LABEL = "View Course Tasks";
    private StudentCoursesState state = new StudentCoursesState();
    public StudentCoursesViewModel(){super("student courses page");}
    public void setState(StudentCoursesState state){this.state = state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public void firePropertyChangedPopup(){support.firePropertyChange("popup", null, this.state);}
    public StudentCoursesState getState(){return state;}
}
