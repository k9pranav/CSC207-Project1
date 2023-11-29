package view;

import interface_adapter.admin_logged_in.AdminLoggedInController;
import interface_adapter.admin_logged_in.AdminLoggedInViewModel;

import javax.swing.*;

public class AdminLoggedInView {
    public final String viewName = "admin home page";
    private final AdminLoggedInController controller;
    private final AdminLoggedInViewModel loginViewModel;
    final JButton logOut;
    // takes them back to landing page

    final JButton AllTasks;
    // takes them to tasks view where they can see list of their tasks
    // & edit them and set marks
}
