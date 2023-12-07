package interface_adapter.student_course_tasks;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StudentCourseTasksViewModel extends ViewModel {
    private StudentCourseTasksState state = new StudentCourseTasksState();

    public StudentCourseTasksViewModel(){super("student course tasks page");}

    public void setState(StudentCourseTasksState state){this.state = state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}

    public void firePropertyChangedPopup(){support.firePropertyChange("popup", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    public StudentCourseTasksState getState(){return state;}

}
