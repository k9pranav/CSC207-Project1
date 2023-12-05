package view;

import entity.Admin;
import interface_adapter.admin_courses.AdminCoursesController;
import interface_adapter.admin_courses.AdminCoursesViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AdminCoursesView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "student courses page";
    private final AdminCoursesViewModel coursesViewModel;
    private final AdminCoursesController coursesController;
    final JList<String> courses;
    final JButton viewTasksButton;
    final JButton exit;

    public AdminCoursesView(AdminCoursesViewModel coursesViewModel, AdminCoursesController coursesController){
        this.coursesViewModel = coursesViewModel;
        this.coursesViewModel.addPropertyChangeListener(this);
        this.coursesController = coursesController;
        Admin admin = coursesViewModel.getState().getLoggedInUser();

        String[] courseCodes = new String[admin.getCourses().size()];
        for (int i = 0; i < admin.getCourses().size(); i++){
            courseCodes[i] = admin.getCourses().get(i).getCourseCode();
        }
        this.courses = new JList<>(courseCodes);

        viewTasksButton = new JButton(coursesViewModel.GET_TASKS_LABEL);

        //add exit button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exit = new JButton("X");
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.RED);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        buttonPanel.add(exit);

        viewTasksButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(viewTasksButton)){
                            String courseCode = courses.getSelectedValue();
                            coursesController.execute(courseCode, coursesViewModel.getState().getLoggedInUser());
                        }
                    }
                }
        );

        exit.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if(evt.getSource().equals(exit)){
                            coursesController.execute("exit", coursesViewModel.getState().getLoggedInUser());
                        }
                    }
                }
        );

        // custom renderer displays buttons next to items
        courses.setCellRenderer(new Renderer(viewTasksButton));

        setLayout(new BorderLayout());
        add(new JScrollPane(courses), BorderLayout.CENTER);
    }

    // cell renderer
    private class Renderer extends JPanel implements ListCellRenderer<String>{
        private final JButton viewTasksButton;

        public Renderer(JButton viewTasksButton){
            this.viewTasksButton = viewTasksButton;
            setLayout(new BorderLayout());
            add(viewTasksButton, BorderLayout.EAST);
        }
        @Override
        public Component getListCellRendererComponent(JList<? extends String> tasks, String value, int index, boolean isSelected, boolean cellHasFocus){
            removeAll();
            add(new JLabel(value), BorderLayout.CENTER);
            add(viewTasksButton, BorderLayout.EAST);
            return this;
        }
    }
    @Override
    public void actionPerformed(ActionEvent evt){
        System.out.println("Click" + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){}
}
