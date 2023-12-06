package view;

import interface_adapter.login_student.LoginStudentController;
import interface_adapter.login_student.LoginStudentState;
import interface_adapter.login_student.LoginStudentViewModel;
import interface_adapter.signup_admin.SignupAdminState;
import interface_adapter.signup_student.SignupStudentState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class StudentLoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "log in student";
    private final LoginStudentController studentLoginController;

    private final LoginStudentViewModel loginViewModel;

    final JTextField emailInputField = new JTextField(15);
    private final JLabel emailErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton cancel;
    public StudentLoginView(LoginStudentViewModel loginViewModel, LoginStudentController studentLoginController){
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.studentLoginController = studentLoginController;

        JLabel title = new JLabel("Student Login");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel("Email"), emailInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField
        );

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        logIn.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(logIn)){
                            studentLoginController.execute(
                                    "login",
                                    loginViewModel.getState().getEmail(),
                                    loginViewModel.getState().getPassword());
                        }
                    }
                }
        );
        cancel.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(cancel)){
                            studentLoginController.execute(
                                    "cancel",
                                    loginViewModel.getState().getEmail(),
                                    loginViewModel.getState().getPassword());
                        }
                    }
                }
        );

        emailInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped (KeyEvent e){
                LoginStudentState currentState = loginViewModel.getState();
                currentState.setEmail(emailInputField.getText()+ e.getKeyChar());
                loginViewModel.setState(currentState);
            }
            @Override
            public void keyPressed (KeyEvent e){
            }

            @Override
            public void keyReleased(KeyEvent e){}

        });

        passwordInputField.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){
                LoginStudentState currentState = loginViewModel.getState();
                currentState.setPassword(passwordInputField.getText()+ e.getKeyChar());
                loginViewModel.setState(currentState);
            }
            @Override
            public void keyPressed(KeyEvent e){}

            @Override
            public void keyReleased(KeyEvent e){}

        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(emailInfo);
        this.add(emailErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt){
        System.out.println("Click" + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        LoginStudentState state = (LoginStudentState) evt.getNewValue();
        if (state.getEmailError() != null) {
            JOptionPane.showMessageDialog(this, state.getEmailError());
        }
    }

    private void setFields(LoginStudentState state){
        emailInputField.setText(state.getEmail());
        passwordInputField.setText(state.getPassword());
    }
}
