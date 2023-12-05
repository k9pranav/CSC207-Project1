package app;

import data_access.FileAdminDataAccessObject;
import data_access.FileStudentDataAccessObject;
import entity.AdminFactory;
import entity.PersonFactory;
import entity.StudentFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_landing_page.AdminLandingPageViewModel;
import interface_adapter.admin_logged_in.AdminLoggedInViewModel;
import interface_adapter.landing_page.LandingPageViewModel;
import interface_adapter.loggedin_student.LoggedinStudentViewModel;
import interface_adapter.login_admin.LoginAdminViewModel;
import interface_adapter.login_student.LoginStudentViewModel;
import interface_adapter.signup_admin.SignupAdminViewModel;
import interface_adapter.signup_student.SignupStudentViewModel;
import interface_adapter.student_landing_page.StudentLandingPageViewModel;
import use_case.login_student.LoginStudentDataAccessInterface;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Landing page");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.

        //Landing page
        LandingPageViewModel landingPageViewModel = new LandingPageViewModel();

        //Student loging/Signup
        StudentLandingPageViewModel studentLandingPageViewModel = new StudentLandingPageViewModel();
        SignupStudentViewModel signupStudentViewModel = new SignupStudentViewModel();
        LoginStudentViewModel loginStudentViewModel = new LoginStudentViewModel();
        LoggedinStudentViewModel loggedInStudentViewModel = new LoggedinStudentViewModel();

        //Admin loging/Signup
        AdminLandingPageViewModel adminLandingPageViewModel = new AdminLandingPageViewModel();
        SignupAdminViewModel signupAdminViewModel = new SignupAdminViewModel();
        LoginAdminViewModel loginAdminViewModel = new LoginAdminViewModel();
        AdminLoggedInViewModel loggedInAdminViewModel = new AdminLoggedInViewModel();
        System.out.println(System.getProperty("user.dir"));

        FileStudentDataAccessObject studentDataAccessObject;

        try {
            studentDataAccessObject = new FileStudentDataAccessObject("./users.json", new StudentFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileAdminDataAccessObject adminDataAccessObject;
        try {
            adminDataAccessObject = new FileAdminDataAccessObject("./users.json", new AdminFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        LandingPageView landingPageView = LandingPageUseCaseFactory.create(viewManagerModel, landingPageViewModel,
                studentLandingPageViewModel, adminLandingPageViewModel);
        views.add(landingPageView, landingPageView.viewName);

        StudentLandingPageView studentLandingPageView = StudentLandingPageUseCaseFactory.create(viewManagerModel,
                studentLandingPageViewModel, signupStudentViewModel, loginStudentViewModel);
        views.add(studentLandingPageView, studentLandingPageView.viewName);

        AdminLandingPageView adminLandingPageView = AdminLandingPageUseCaseFactory.create(viewManagerModel,
                adminLandingPageViewModel, signupAdminViewModel, loginAdminViewModel);
        views.add(adminLandingPageView, adminLandingPageView.viewName);

        StudentSignupView signupView = SignupStudentUseCaseFactory.create(viewManagerModel, loginStudentViewModel, signupStudentViewModel,
                studentDataAccessObject);
        views.add(signupView, signupView.viewName);

        AdminSignupView adminsignupView = SignupAdminUseCaseFactory.create(viewManagerModel, loginAdminViewModel, signupAdminViewModel,
                adminDataAccessObject);
        views.add(adminsignupView, adminsignupView.viewName);

        StudentLoginView loginStudentView = LoginStudentUseCaseFactory.create(viewManagerModel, loginStudentViewModel, loggedInStudentViewModel, studentDataAccessObject);
        views.add(loginStudentView, loginStudentView.viewName);

        AdminLoginView loginAdminView = LoginAdminUseCaseFactory.create(viewManagerModel, loginAdminViewModel,
                loggedInAdminViewModel, adminDataAccessObject);
        views.add(loginAdminView, loginAdminView.viewName);

        StudentLoggedInView loggedInStudentView = new StudentLoggedInView(loggedInStudentViewModel);
        views.add(loggedInStudentView, loggedInStudentView.viewName);

        //TODO: Adrien when Json stuff works
        // AdminLoggedInView loggedInAdminView = new AdminLoggedInView(loggedInAdminViewModel);
        // views.add(loggedInAdminView, loggedInAdminView.viewName);

        viewManagerModel.setActiveView(landingPageView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}