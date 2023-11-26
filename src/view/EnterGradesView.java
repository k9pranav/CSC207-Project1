package view;

import entity.CourseTask;
import entity.Student;
import interface_adapter.enter_grades.EnterGradesController;
import interface_adapter.enter_grades.EnterGradesViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class EnterGradesView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "enter grades page";
    private final EnterGradesViewModel viewModel;
    private final EnterGradesController controller;
    private JTextField[] gradeInputField;
    final JButton exit;

    public EnterGradesView(EnterGradesViewModel viewModel, EnterGradesController controller){
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.controller = controller;

        CourseTask currentTask = viewModel.getState().getTask();

        ArrayList<Student> studentList = currentTask.getCourse().getStudentsEnrolled();

        String[] studentStrings = new String[studentList.size()];
        for (int i = 0; i < studentList.size(); i++) {
            studentStrings[i] = studentList.get(i).getFirstName() + " " + studentList.get(i).getLastName() + ": " + studentList.get(i).getEmail();
            // string format: "FirstName LastName: email"
        }
        this.gradeInputField = new JTextField[studentStrings.length];
        setLayout(new GridLayout(studentStrings.length, 2)); // one column for string one for input field
        for (int i = 0; i < studentStrings.length; i++) {
            add(new JLabel(studentStrings[i]));
            add(gradeInputField[i] = new JTextField());
            // set initial value, which is student's current grade for the task
        }

    }
}
