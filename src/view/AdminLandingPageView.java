package view;
import interface_adapter.admin_landing_page.AdminLandingPageController;
import interface_adapter.admin_landing_page.AdminLandingPageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AdminLandingPageView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "admin landing page";
    private final AdminLandingPageViewModel adminLandingPageViewModel;
    private final AdminLandingPageController adminLandingPageController;
    private final JButton signUp;
    private final JButton login;

    public AdminLandingPageView(AdminLandingPageController adminLandingPageController, AdminLandingPageViewModel adminlandingPageViewModel){
        this.adminLandingPageController = adminLandingPageController;
        this.adminLandingPageViewModel = adminlandingPageViewModel;
        adminLandingPageViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(adminLandingPageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        signUp = new JButton(adminlandingPageViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        login = new JButton(adminlandingPageViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(login);

        signUp.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(signUp)){
                            adminLandingPageController.execute("signup");
                        }
                    }
                }
        );
        login.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(login)){
                            adminLandingPageController.execute("login");
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
