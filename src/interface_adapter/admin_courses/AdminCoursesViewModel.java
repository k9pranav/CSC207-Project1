package interface_adapter.admin_courses;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AdminCoursesViewModel extends ViewModel {
    public final String GET_TASKS_LABEL = "View Course Tasks";
    private AdminCoursesState state = new AdminCoursesState();
    public AdminCoursesViewModel(){super("student courses page");}
    public void setState(AdminCoursesState state){this.state = state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    public AdminCoursesState getState(){return state;}

}
