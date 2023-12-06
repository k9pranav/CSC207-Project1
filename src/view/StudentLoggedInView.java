package view;

import interface_adapter.student_logged_in.StudentLoggedInController;
import interface_adapter.student_logged_in.StudentLoggedInState;
import interface_adapter.student_logged_in.StudentLoggedInViewModel;
import interface_adapter.student_logged_in.StudentLoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StudentLoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in student";
    private final StudentLoggedInViewModel studentLoggedInViewModel;
    private final StudentLoggedInController studentLoggedInController;
    JLabel email;
    private final JButton coursesButton;

    //final JButton logOut;

    /**
     * A window with a title and a JButton.
     */
    public StudentLoggedInView(StudentLoggedInController controller, StudentLoggedInViewModel studentloggedInViewModel) {
        this.studentLoggedInViewModel = studentloggedInViewModel;
        this.studentLoggedInViewModel.addPropertyChangeListener(this);
        this.studentLoggedInController = controller;
        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailInfo = new JLabel("Currently logged in: ");
        email = new JLabel();

        JPanel buttons = new JPanel();
        //logOut = new JButton(loggedInStudentViewModel.LOGOUT_BUTTON_LABEL);
        //buttons.add(logOut);
        //logOut.addActionListener(this);
        coursesButton = new JButton(studentloggedInViewModel.COURSES_BUTTON_LABEL);
        buttons.add(coursesButton);
        coursesButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(coursesButton)){
                            studentLoggedInController.execute("courses");
                        }
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(emailInfo);
        this.add(email);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        StudentLoggedInState state = (StudentLoggedInState) evt.getNewValue();
        email.setText(state.getEmail());
    }
}
