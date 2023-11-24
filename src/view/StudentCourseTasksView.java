package view;

import entity.CourseTask;
import interface_adapter.student_course_tasks.StudentCourseTasksController;
import interface_adapter.student_course_tasks.StudentCourseTasksViewModel;
import interface_adapter.student_tasks.StudentTasksState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class StudentCourseTasksView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "student course tasks page";

    private final StudentCourseTasksViewModel tasksViewModel;
    private final StudentCourseTasksController tasksController;

    final JList<String> tasks;
    final JButton exit;
    public StudentCourseTasksView(StudentCourseTasksViewModel tasksViewModel, StudentCourseTasksController tasksController){
        this.tasksViewModel = tasksViewModel;
        this.tasksViewModel.addPropertyChangeListener(this);
        this.tasksController = tasksController;
        String courseCode = tasksViewModel.getState().getCourse().getCourseCode();

        setLayout(new BorderLayout());

        JLabel title = new JLabel(courseCode);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        ArrayList<CourseTask> allTasks = tasksViewModel.getState().getTasks();
        String[] str = new String[allTasks.size()];
        for (int i = 0; i < allTasks.size(); i++) {
            str[i] = allTasks.get(i).getTaskName();
        }
        this.tasks = new JList<>(str);
        JScrollPane scrollPane = new JScrollPane(tasks);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exit = new JButton("X");
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.RED);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        buttonPanel.add(exit);

        add(scrollPane, BorderLayout.EAST);

        tasks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // open popup w/ task info
                    // Double-click detected
                    tasksController.execute(tasks.getSelectedValue(), taskViewModel.getState().getLoggedInUser());
                }
            }
        });

        exit.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(exit)){
                            tasksController.execute("exit", tasksViewModel.getState.getLoggedInUser());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttonPanel);
    }
    public void actionPerformed(ActionEvent evt){System.out.println("Click" + evt.getActionCommand());}

    public void propertyChange(PropertyChangeEvent evt){
        String propName = evt.getPropertyName();
        if (propName.equals("popup")) {
            StudentTasksState currState = (StudentTasksState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, currState.getCurrentTaskInfo());
        }
    }

}
