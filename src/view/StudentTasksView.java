package view;

import com.eduworks.ec.task.Task;
import entity.Student;
import interface_adapter.student_tasks.StudentTasksController;
import interface_adapter.student_tasks.StudentTasksState;
import interface_adapter.student_tasks.StudentTasksViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


public class StudentTasksView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "student tasks page";
    private final StudentTasksViewModel taskViewModel;
    private final StudentTasksController taskController;
    private final ArrayList<entity.Task> allTasks;
    final JButton newStudentTask;

    final JButton exit;
    final JList<String> tasks;
    // display a list of all their tasks, need to implement scroll bar too
    // logged in state and this state needs to always keep track of the Student that's logged in
    public StudentTasksView(StudentTasksViewModel taskViewModel, StudentTasksController taskController){
        this.taskViewModel = taskViewModel;
        this.taskViewModel.addPropertyChangeListener(this);
        this.taskController = taskController;
        Student student = taskViewModel.getState().getLoggedInUser();
        String title1 = (student.getFirstName() + "'s Tasks");

        setLayout(new BorderLayout()); // main layout manager

        JLabel title = new JLabel(title1);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // make JList with tasks
        ArrayList<entity.Task> allTasks = student.getTasks();
        this.allTasks = allTasks;
        String[] str = new String[allTasks.size()];
        for (int i = 0; i < allTasks.size(); i++) {
            str[i] = allTasks.get(i).getTaskName();
        }
        this.tasks = new JList<>(str);
        JScrollPane scrollPane = new JScrollPane(tasks);

        // red x button in top left corner
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exit = new JButton("X");
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.RED);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        buttonPanel.add(exit);
        newStudentTask = new JButton(taskViewModel.NEW_STUDENT_TASK_LABEL);
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.EAST);
        JPanel secondButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        secondButtonPanel.add(newStudentTask, BorderLayout.SOUTH);


        // Add double-click listener to the JList
        // opens popup with task description + edit if its a student task
        tasks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Double-click detected
                    taskController.execute(tasks.getSelectedValue(), taskViewModel.getState().getLoggedInUser());
                }
            }
        });

        newStudentTask.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(newStudentTask)){
                            taskController.execute("new task", taskViewModel.getState().getLoggedInUser());
                        }
                    }
                }
        );
        exit.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(exit)){
                            taskController.execute("exit", taskViewModel.getState().getLoggedInUser());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttonPanel);
        this.add(secondButtonPanel);
    }

    public void actionPerformed(ActionEvent evt){System.out.println("Click" + evt.getActionCommand());}

    public void propertyChange(PropertyChangeEvent evt) {
        String propName = evt.getPropertyName();
        if (propName.equals("popup")) {
            StudentTasksState currState = (StudentTasksState) evt.getNewValue();
            // if its a CourseTask, no edit button
            if (currState.getCurrentTaskInfo().contains("Weight:") &&
                    currState.getCurrentTaskInfo().contains("Grade:") &&
                    currState.getCurrentTaskInfo().contains("Course:")){
                // student cannot edit, no edit button
                JOptionPane.showMessageDialog(this, currState.getCurrentTaskInfo());
            }else{
                JPanel panel = new JPanel();
                JLabel taskInfoLabel = new JLabel(currState.getCurrentTaskInfo());
                JButton editTaskButton = new JButton("Edit");
                panel.add(taskInfoLabel);
                panel.add(editTaskButton);

                editTaskButton.addActionListener(e -> {
                    // call the presenter ?
                    // open the edit task view
                });

                JOptionPane.showMessageDialog(this, panel);
            }
        }
    }
}
