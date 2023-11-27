package interface_adapter.edit_course_task;

import interface_adapter.ViewModel;
import view.EditCourseTaskView;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class EditCourseTaskViewModel extends ViewModel {

    public static final String SAVE_BUTTON_LABEL = "Save";
    public static final String EXIT_BUTTON_LABEL = "Exit";

    private static EditCourseTaskState state = new EditCourseTaskState();

    public EditCourseTaskViewModel(){super("Edit Task Course");}
    public void setState(EditCourseTaskState state) {
        this.state = state;
    }

    public static EditCourseTaskState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
