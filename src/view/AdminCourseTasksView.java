package view;

import entity.CourseTask;
import interface_adapter.admin_course_tasks.AdminCourseTasksController;
import interface_adapter.admin_course_tasks.AdminCourseTasksViewModel;
import interface_adapter.admin_tasks.AdminTasksViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class AdminCourseTasksView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "admin course tasks page";
    private final AdminCourseTasksViewModel tasksViewModel;
    private final AdminCourseTasksController tasksController;
    final JList<String> tasks;
    final JButton newCourseTask;
    final JButton exit;
    // goes back to courses view
    // double click on task to open edit task view

    public AdminCourseTasksView(AdminCourseTasksViewModel tasksViewModel, AdminCourseTasksController tasksController){
        this.tasksViewModel = tasksViewModel;
        this.tasksViewModel.addPropertyChangeListener(this);
        this.tasksController = tasksController;
        // the tasks for the course that was clicked are stored in the state
        // current course is also stored in the state
        String courseName = tasksViewModel.getState().getCourse().getCourseCode();
        setLayout(new BorderLayout());
        JLabel title = new JLabel(courseName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        // title is the current course

        // create JList with tasks from the state
        ArrayList<CourseTask> allTasks = tasksViewModel.getState().getTasks();
        String[] str = new String[allTasks.size()];
        for (int i = 0; i < allTasks.size(); i++) {
            str[i] = allTasks.get(i).getTaskName();
        }
        this.tasks = new JList<>(str);
        JScrollPane scrollPane = new JScrollPane(tasks);

        // red x button in corner
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exit = new JButton("X");
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.RED);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        buttonPanel.add(exit);
        newCourseTask = new JButton(tasksViewModel.NEW_COURSE_TASK_LABEL);
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.EAST);
        JPanel secondButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        secondButtonPanel.add(newCourseTask, BorderLayout.SOUTH);

        // opens edit task view for the task selected
        tasks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Double-click detected
                    // getSelectedValue is task name (unique)
                    tasksController.execute(tasks.getSelectedValue(), tasksViewModel.getState().getLoggedInUser());
                }
            }
        });
        exit.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if ((evt.getSource().equals(exit))){
                            tasksController.execute("exit", tasksViewModel.getState().getLoggedInUser());
                        }
                    }
                }
        );
        newCourseTask.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(newCourseTask)){
                            tasksController.execute("new task", tasksViewModel.getState().getLoggedInUser());
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

    public void propertyChange(PropertyChangeEvent evt){}
}
