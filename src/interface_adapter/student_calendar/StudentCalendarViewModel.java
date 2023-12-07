package interface_adapter.student_calendar;

import interface_adapter.ViewModel;
import interface_adapter.student_tasks.StudentTasksState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StudentCalendarViewModel extends ViewModel {
    private StudentCalendarState state = new StudentCalendarState();
    public StudentCalendarViewModel(){
        super("student calendar page");
    }

    public void setState(StudentCalendarState state){
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}

    public void firePropertyChangedPopup(){support.firePropertyChange("popup", null, this.state);}
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public StudentCalendarState getState() {
        return state;
    }
}