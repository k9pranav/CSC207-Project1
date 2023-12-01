package interface_adapter.admin_tasks;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AdminTasksViewModel extends ViewModel{
    public final String NEW_COURSE_TASK_LABEL = "Create New Task";
    private AdminTasksState state = new AdminTasksState();
    public AdminTasksViewModel(){super("admin tasks page");}

    public void setState(AdminTasksState state){this.state = state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    public AdminTasksState getState(){return state;}

    public void firePropertyChangedPopup() {support.firePropertyChange("state", null, this.state);}
}
