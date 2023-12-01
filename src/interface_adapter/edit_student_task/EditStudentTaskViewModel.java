package interface_adapter.edit_student_task;

import interface_adapter.ViewModel;
import interface_adapter.edit_course_task.EditCourseTaskState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EditStudentTaskViewModel extends ViewModel {

    public static final String CREATE_BUTTON_LABEL = "Create";
    public static final String EXIT_BUTTON_LABEL = "Exit";

    private static EditStudentTaskState state = new EditStudentTaskState();

    public EditStudentTaskViewModel(){super("Edit Student Tasks");}

    public void setState(EditStudentTaskState state) {
        this.state = state;
    }

    public static EditStudentTaskState getState(){return state;}

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
