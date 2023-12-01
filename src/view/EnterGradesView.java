package view;

import entity.CourseTask;
import entity.Student;
import interface_adapter.enter_grades.EnterGradesController;
import interface_adapter.enter_grades.EnterGradesState;
import interface_adapter.enter_grades.EnterGradesViewModel;
import interface_adapter.login_admin.LoginAdminState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

public class EnterGradesView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "enter grades page";
    private final EnterGradesViewModel viewModel;
    private final EnterGradesController controller;
    private JTextField[] gradeInputField;
    final JButton exit;
    final JButton releaseGrades;

    public EnterGradesView(EnterGradesViewModel viewModel, EnterGradesController controller){
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.controller = controller;

        CourseTask currentTask = viewModel.getState().getTask();
        Student studentLoggedIn = viewModel.getState().getLoggedInUser();

        ArrayList<Student> studentList = currentTask.getCourse().getStudentsEnrolled();

        String[] studentStrings = new String[studentList.size()];
        for (int i = 0; i < studentList.size(); i++) {
            studentStrings[i] = studentList.get(i).getFirstName() + " " + studentList.get(i).getLastName() + ": " + studentList.get(i).getEmail();
            // string format: "FirstName LastName: email"
        }

        this.gradeInputField = new JTextField[studentStrings.length];

        JPanel contentPanel = new JPanel(new GridLayout(studentStrings.length, 2));
        // one column for string one for input field

        for (int i = 0; i < studentStrings.length; i++) {
            contentPanel.add(new JLabel(studentStrings[i]));
            contentPanel.add(gradeInputField[i] = new JTextField(String.valueOf(studentLoggedIn.getTaskGrade(currentTask.getTaskName()))));
            // set initial value, which is student's current grade for the task

            // add action listener to each field
            gradeInputField[i].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e){
                    // get the index of which one was typed next to
                    // in current state have a hashmap of student to grade

                    EnterGradesState currentState = viewModel.getState();

                    JTextField sourceField = (JTextField) e.getSource();
                    int studentIndex = Arrays.asList(gradeInputField).indexOf(sourceField);
                    String studentUsername = studentStrings[studentIndex].substring(((studentStrings[studentIndex]).indexOf(": ")+2));

                    currentState.setGrade(studentUsername, sourceField.getText());
                    viewModel.setState(currentState);
                }

                @Override
                public void keyPressed(KeyEvent e){}
                @Override
                public void keyReleased(KeyEvent e){}
            });
        }

        //add exit button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exit = new JButton("X");
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.RED);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        buttonPanel.add(exit);

        releaseGrades = new JButton("Release Grades");
        releaseGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(releaseGrades)){
                    controller.execute("release", viewModel.getState.getLoggedInUser());
                }
            }
        });

        add(releaseGrades, BorderLayout.SOUTH);
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        exit.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if(evt.getSource().equals(exit)){
                            controller.execute("exit", viewModel.getState.getLoggedInUser());
                        }
                    }
                }
        );

    }
    public void actionPerformed(ActionEvent evt){System.out.println("Click" + evt.getActionCommand());
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        // implement
    }

    /*
     What's in the Login Admin View for reference:
        @Override
        public void propertyChange(PropertyChangeEvent evt){
            LoginAdminState state = (LoginAdminState) evt.getNewValue();
            setFields(state);
        }

        private void setFields(LoginAdminState state){
            usernameInputField.setText(state.getUsername());
            passwordInputField.setText(state.getPassword());
        }
     */
}
