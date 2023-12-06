package view;

import entity.Admin;
import entity.Course;
import entity.Student;
import interface_adapter.student_courses.StudentCoursesController;
import interface_adapter.student_courses.StudentCoursesState;
import interface_adapter.student_courses.StudentCoursesViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class StudentCoursesView extends JPanel implements ActionListener, PropertyChangeListener{
    // displays all courses a student has with a view all tasks button next to each one
    public final String viewName = "student courses page";
    private final StudentCoursesViewModel coursesViewModel;
    private final StudentCoursesController coursesController;
    final JList<String> courses;
    final JButton getAverage;
    final JButton viewTasksButton;
    final JButton exit;

    public StudentCoursesView(StudentCoursesViewModel coursesViewModel, StudentCoursesController coursesController){
        this.coursesViewModel = coursesViewModel;
        this.coursesViewModel.addPropertyChangeListener(this);
        this.coursesController = coursesController;

        /* TODO usuless as it is called by the main
        Student student = coursesViewModel.getState().getLoggedInUser();

        String[] courseStrings = new String[student.getCourses().size()];
        for (int i = 0; i < student.getCourses().size(); i++){
            courseStrings[i] = student.getCourses().get(i).getCourseCode() + ": " + student.getCourses().get(i).getCourseName();
        }

         */
        String[] courseStrings = new String[1];
        courseStrings[0] = "hello";
        this.courses = new JList<>(courseStrings);

        viewTasksButton = new JButton(coursesViewModel.GET_TASKS_LABEL);

        getAverage = new JButton(coursesViewModel.GET_AVERAGE_LABEL);

        //add exit button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exit = new JButton("X");
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.RED);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        buttonPanel.add(exit);

        getAverage.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(getAverage)){
                            coursesController.execute("get average", coursesViewModel.getState().getLoggedInUser());
                        }
                    }
                }
        );

        viewTasksButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(viewTasksButton)){
                            String courseCodeAndName = courses.getSelectedValue();
                            coursesController.execute(courseCodeAndName, coursesViewModel.getState().getLoggedInUser());
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
        // check what this looks like
        add(getAverage, BorderLayout.SOUTH);
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
    public void propertyChange(PropertyChangeEvent evt){
        String propName = evt.getPropertyName();
        Student student = coursesViewModel.getState().getLoggedInUser();

        String[] courseStrings = new String[student.getCourses().size()];
        for (int i = 0; i < student.getCourses().size(); i++){
            courseStrings[i] = student.getCourses().get(i).getCourseName() + ": " + student.getCourses().get(i).getCourseDescription();
        }
        this.courses.setListData(courseStrings);
        if (propName.equals("popup")){
            // display the average popup
            StudentCoursesState currState = (StudentCoursesState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, currState.getAverage());
        }
    }
}
