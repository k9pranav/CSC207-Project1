package view;

import interface_adapter.edit_student_task.EditStudentTaskController;
import interface_adapter.edit_student_task.EditStudentTaskState;
import interface_adapter.edit_student_task.EditStudentTaskViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class EditStudentTasksView extends JPanel implements ActionListener,
        PropertyChangeListener {

    public final String viewName = "edit student course page";

    final JTextField taskNameField = new JTextField(15);

    final JTextField taskTypeField = new JTextField(15);

    final JTextField deadlineField = new JTextField(15);

    final JButton create;

    final JButton exit;

    private final EditStudentTaskViewModel editStudentTaskViewModel;
    private final EditStudentTaskController editStudentTaskController;

    private final StudentLoggedInView studentLoggedInView;

    public EditStudentTasksView(EditStudentTaskViewModel editStudentTaskViewModel,
                                EditStudentTaskController editStudentTaskController,
                                StudentLoggedInView studentLoggedInView, StudentLoggedInView studentLoggedInView1) {

        this.editStudentTaskViewModel = editStudentTaskViewModel;
        this.editStudentTaskController = editStudentTaskController;
        this.studentLoggedInView = studentLoggedInView;


        //Adding Fields and Buttons
        JLabel title = new JLabel("Edit Student Tasks");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskNameInfo = new LabelTextPanel(
                new JLabel("Task Name"), taskNameField);

        LabelTextPanel taskTypeInfo = new LabelTextPanel(
                new JLabel("Task Type"), taskTypeField);

        LabelTextPanel taskDeadlineInfo = new LabelTextPanel(
                new JLabel("Task Deadline"), deadlineField);

        JPanel Buttons = new JPanel();
        create = new JButton(EditStudentTaskViewModel.CREATE_BUTTON_LABEL);
        Buttons.add(create);

        exit = new JButton(EditStudentTaskViewModel.EXIT_BUTTON_LABEL);
        Buttons.add(exit);

        //Adding Key Listeners for my Field
        taskNameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                EditStudentTaskState currentState = EditStudentTaskViewModel.getState();
                currentState.setTaskName(taskNameField.getText() + e.getKeyChar());
                editStudentTaskViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        deadlineField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                EditStudentTaskState currentState = EditStudentTaskViewModel.getState();
                currentState.setDeadline(deadlineField.getText() + e.getKeyChar());
                editStudentTaskViewModel.setState(currentState);

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        taskTypeField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                EditStudentTaskState currentState = EditStudentTaskViewModel.getState();
                currentState.setTaskType(taskTypeField.getText() + e.getKeyChar());
                editStudentTaskViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        exit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(exit)){
                            editStudentTaskViewModel.firePropertyChanged();                       }
                    }
                }
        );

        create.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(create)){
                        EditStudentTaskState editStudentTaskState =
                                editStudentTaskViewModel.getState();

                        editStudentTaskController.execute(
                                editStudentTaskState.getTaskName(),
                                editStudentTaskState.getTaskType(),
                                editStudentTaskState.getDeadline(),
                                //Need to figure out how to get emails
                                studentLoggedInView.getState().getEmail()



                        );
                        }
                    }
                }
        );

        this.add(title);
        this.add(taskNameInfo);
        this.add(taskTypeInfo);
        this.add(taskDeadlineInfo);


    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
