package view;

import entity.Admin;
import entity.CourseTask;
import interface_adapter.admin_course_tasks.AdminCourseTasksState;
import interface_adapter.admin_tasks.AdminTasksController;
import interface_adapter.admin_tasks.AdminTasksState;
import interface_adapter.admin_tasks.AdminTasksViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class AdminTasksView extends JPanel implements ActionListener, PropertyChangeListener {
public final String viewName = "admin tasks page";
private final AdminTasksViewModel tasksViewModel;
private final AdminTasksController tasksController;
private final ArrayList<CourseTask> allTasks;
final JButton newCourseTask;
final JButton exit;
final JList<String> tasks;

public AdminTasksView(AdminTasksViewModel tasksViewModel, AdminTasksController tasksController){
    this.tasksViewModel = tasksViewModel;
    this.tasksViewModel.addPropertyChangeListener(this);
    this.tasksController = tasksController;
    Admin admin = tasksViewModel.getState().getLoggedInUser();
    String title1 = (admin.getFirstName() + "'s Tasks");

    setLayout(new BorderLayout());

    JLabel title = new JLabel(title1);
    title.setAlignmentX(Component.CENTER_ALIGNMENT);

    ArrayList<CourseTask> allTasks = admin.getTasks();
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
                tasksController.execute(tasks.getSelectedValue(), tasksViewModel.getState().getLoggedInUser());
            }
        }
    });

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

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(title);
    this.add(buttonPanel);
    this.add(secondButtonPanel);
}
public void actionPerformed(ActionEvent evt){System.out.println("Click" + evt.getActionCommand());}

public void propertyChange(PropertyChangeEvent evt){
    String propName = evt.getPropertyName();
    if (propName.equals("popup")) {
        AdminTasksState currState = (AdminTasksState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, currState.getCurrentTaskInfo());
    }
}
}


