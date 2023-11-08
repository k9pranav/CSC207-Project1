package view;

import interface_adapter.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class LandingPageView {
    public final String viewName = 'home';
    private final LandingPageViewModel landingPageViewModel;
    private final SignupStudentController studentSignupController;
    private final SignupAdminController adminSignupController;
    private final JButton student;
    private final JButton admin;

    public LandingPageView(StudentSignupController studentSignupController, AdminSignupController adminSignupController, LandingPageViewModel landingPageViewModel){
        this.landingPageController = controller;
        this.landingPageViewModel = landingPageViewModel;
        landingPageViewModel.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        student = new JButton(landingPageViewModel.STUDENT_BUTTON_LABEL);
        buttons.add(student);
        admin = new JButton(signupViewModel.ADMIN_BUTTON_LABEL);
        buttons.add(admin);

        student.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(student)){
                            studentSignupController.execute();
                        }
                    }
                }
        );
        admin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(admin)){
                            adminSignupController.execute();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttons);
    }
}
