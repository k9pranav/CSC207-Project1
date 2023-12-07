package view;

import entity.Admin;
import interface_adapter.edit_course_task.EditCourseTaskController;
import interface_adapter.edit_course_task.EditCourseTaskState;
import interface_adapter.edit_course_task.EditCourseTaskViewModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditCourseTaskView extends JPanel implements ActionListener,
        PropertyChangeListener {
    public final String viewName = "edit courses tasks page";
    private final EditCourseTaskViewModel editCourseTaskViewModel;
    private final EditCourseTaskController editCourseTaskController;

    //Adding fields
    //Task Name field
    final JTextField taskNameField = new JTextField(15);

    //Task Course Name
    final JTextField taskCourseName = new JTextField(15);
    //Error Field. Will pop up if the admin does not have the course
    private final JLabel taskCourseErrorName = new JLabel();

    //Task type
    final JTextField taskType = new JTextField(15);

    //Task deadline. For now, it is a text file, because JDateChooser is acting up
    final JTextField taskDeadline = new JTextField(15);

    //Task Weight. Need to add code to check if its float or not
    final JTextField taskWeight = new JTextField(15);

    // TODO final JButton save;
    final JButton exit;






    public EditCourseTaskView(EditCourseTaskViewModel editCourseTaskViewModel,
                              EditCourseTaskController editCourseTaskController, JButton save) {
        this.editCourseTaskViewModel = editCourseTaskViewModel;
        this.editCourseTaskViewModel.addPropertyChangeListener(this);
        this.editCourseTaskController = editCourseTaskController;


        JLabel title = new JLabel("Edit Task Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskNameInfo = new LabelTextPanel(
                new JLabel("Task Name: "), taskNameField);

        LabelTextPanel taskCourseInfo = new LabelTextPanel(
                new JLabel("Task Course: "), taskCourseName);

        LabelTextPanel taskTypeInfo = new LabelTextPanel(
                new JLabel("Task Type: "), taskType);

        LabelTextPanel taskDeadlineInfo = new LabelTextPanel(
                new JLabel("Task Dead Line: "), taskDeadline);

        LabelTextPanel taskWeightInfo = new LabelTextPanel(
                new JLabel("Task Weight: "), taskWeight);

        JPanel Buttons = new JPanel();
        save = new JButton(editCourseTaskViewModel.SAVE_BUTTON_LABEL);
        Buttons.add(save);
        exit = new JButton(editCourseTaskViewModel.EXIT_BUTTON_LABEL);
        Buttons.add(exit);


        //Adding Key Listeners for my fields

        taskNameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                EditCourseTaskState currentState = EditCourseTaskViewModel.getState();
                currentState.setTaskName(taskNameField.getText() + e.getKeyChar());
                editCourseTaskViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        taskCourseName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                EditCourseTaskState currentState = EditCourseTaskViewModel.getState();
                currentState.setTaskCourseName(taskCourseName.getText() + e.getKeyChar());
                editCourseTaskViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        taskType.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                EditCourseTaskState currentState = EditCourseTaskViewModel.getState();
                currentState.setTaskType(taskType.getText() + e.getKeyChar());
                editCourseTaskViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        taskDeadline.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                EditCourseTaskState currentState = EditCourseTaskViewModel.getState();
                currentState.setTaskDeadline(taskDeadline.getText() + e.getKeyChar());
                editCourseTaskViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        taskWeight.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                EditCourseTaskState currentState = EditCourseTaskViewModel.getState();
                //Checking if its appropriate type, ie weight
                try{
                    float weightValue = Float.parseFloat(taskWeight.getText());
                    currentState.setTaskWeight(weightValue + e.getKeyChar());

                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Invalid Input. " +
                            "Enter a float value");
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.add(title);
        this.add(taskNameInfo);
        this.add(taskCourseInfo);
        this.add(taskCourseErrorName);
        this.add(taskTypeInfo);
        this.add(taskDeadlineInfo);
        this.add(taskWeightInfo);
        this.add(Buttons);





        setLayout(new BorderLayout());




    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click" + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
