package view;


import interface_adapter.landing_page.LandingPageController;
import interface_adapter.landing_page.LandingPageViewModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class LandingPageView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "Landing Page";
    private final LandingPageViewModel landingPageViewModel;
    private final LandingPageController landingPageController;
    private final JButton student;
    private final JButton admin;

    public LandingPageView(LandingPageController landingPageController, LandingPageViewModel landingPageViewModel){
        this.landingPageController = landingPageController;
        this.landingPageViewModel = landingPageViewModel;
        landingPageViewModel.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        student = new JButton(landingPageViewModel.STUDENT_BUTTON_LABEL);
        buttons.add(student);
        admin = new JButton(landingPageViewModel.ADMIN_BUTTON_LABEL);
        buttons.add(admin);

        student.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(student)){
                            landingPageController.execute("student");
                        }
                    }
                }
        );
        admin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(admin)){
                            landingPageController.execute("admin");
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
    }
    public void propertyChange(PropertyChangeEvent evt){
    }
}
