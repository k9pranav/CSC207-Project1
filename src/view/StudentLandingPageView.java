package view;
import interface_adapter.admin_landing_page.AdminLandingPageController;
import interface_adapter.admin_landing_page.AdminLandingPageViewModel;
import interface_adapter.student_landing_page.StudentLandingPageController;
import interface_adapter.student_landing_page.StudentLandingPageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StudentLandingPageView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "admin landing page";
    private final StudentLandingPageViewModel studentLandingPageViewModel;
    private final StudentLandingPageController studentLandingPageController;
    private final JButton signUp;
    private final JButton login;

    public StudentLandingPageView(StudentLandingPageController studentLandingPageController, StudentLandingPageViewModel studentlandingPageViewModel){
        this.studentLandingPageController = studentLandingPageController;
        this.studentLandingPageViewModel = studentlandingPageViewModel;
        studentLandingPageViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(studentLandingPageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        signUp = new JButton(studentlandingPageViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        login = new JButton(studentlandingPageViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(login);

        signUp.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(signUp)){
                            studentLandingPageController.execute("signup");
                        }
                    }
                }
        );
        login.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(login)){
                            studentLandingPageController.execute("login");
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);


    }
    public void actionPerformed(ActionEvent evt) {

    }
    public void propertyChange(PropertyChangeEvent evt){

    }


}
