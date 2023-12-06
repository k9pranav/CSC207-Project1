package view;

mport app.Main;
import app.Main;
import data_access.FileStudentDataAccessObject;
import entity.PersonFactory;
import entity.StudentFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;



//Tests if the student signup and login works for not

public class StudentSignUpTest {

    static String message = "";

    static boolean SignUpWorks = false;

    //Adding two students
    public void addTwoStudents (){
        StudentFactory sFactory = new StudentFactory();
        FileStudentDataAccessObject fstudentDAO;
        try {
            fstudentDAO = new FileStudentDataAccessObject("./users_students.json",
                    sFactory);
        } catch (IOException e){
            throw  new RuntimeException(e);
        }
        fstudentDAO.save(sFactory.create("Pranav", "Kansal",
                "MyPassword", "MyPassword",
                "pranav.kansal@mail.utoronto.ca"));

        fstudentDAO.save(sFactory.create("Bugs", "Bunny",
                "rabbits", "rabbits",
                "bugs.bunny@mail.utoronto.ca"));

    }

    private JButton getStudentLandingPageButton(){
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        LandingPageView sv = (LandingPageView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(1);

        //This should be the Student button in the Landing page. therefore
        JButton SlandingPageButton = (JButton) buttons.getComponent(0);

        //Clicking the button, and going to student landing page
        return SlandingPageButton;
    }

    public JButton getStudentLoginPageButton(){
        JButton studentLandingPage = getStudentLandingPageButton();
        getStudentLandingPageButton().doClick();

        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        StudentLandingPageView sv = (StudentLandingPageView) jp2.getComponent(0);
        JPanel buttons = (JPanel) sv.getComponent(1);

        JButton studentLogIn = (JButton) buttons.getComponent(1);

        return studentLogIn;

    }

    public void testStudentLogin(){
        addTwoStudents();
        Main.main(null);
        JButton studentLandingPageButton = getStudentLandingPageButton();
        studentLandingPageButton.doClick();

        JButton studentLoginPage = getStudentLoginPageButton();
        studentLoginPage.doClick();




    }






}
