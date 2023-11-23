package interface_adapter.admin_course_tasks;

import entity.Admin;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AdminCourseTasksViewModel extends ViewModel {
    public final String NEW_COURSE_TASK_LABEL = "Create New Task";
    private AdminCourseTasksState state = new AdminCourseTasksState();
    public AdminCourseTasksViewModel(){super("admin course tasks page");}
    public void setState(AdminCourseTasksState state){this.state = state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    public AdminCourseTasksState getState(){return state;}
}
