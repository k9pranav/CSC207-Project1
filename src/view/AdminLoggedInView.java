package view;

import interface_adapter.admin_logged_in.AdminLoggedInController;
import interface_adapter.admin_logged_in.AdminLoggedInState;
import interface_adapter.admin_logged_in.AdminLoggedInViewModel;
import interface_adapter.student_logged_in.StudentLoggedInState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AdminLoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in admin";
    private final AdminLoggedInViewModel adminLoggedInViewModel;
    //final JButton logOut;
    // takes them back to landing page
    JLabel email;
    //final JButton AllTasks;

    public AdminLoggedInView(AdminLoggedInViewModel loginViewModel) {
        this.adminLoggedInViewModel = loginViewModel;
        this.adminLoggedInViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailInfo = new JLabel("Currently logged in: ");
        email = new JLabel();

        JPanel buttons = new JPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(emailInfo);
        this.add(email);
        this.add(buttons);
    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AdminLoggedInState state = (AdminLoggedInState) evt.getNewValue();
        email.setText(state.getEmail());
    }
}
