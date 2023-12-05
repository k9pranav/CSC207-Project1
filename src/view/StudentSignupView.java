package view;

import interface_adapter.signup_student.SignupStudentController;
import interface_adapter.signup_student.SignupStudentState;
import interface_adapter.signup_student.SignupStudentViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StudentSignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up student";

    private final SignupStudentViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JTextField firstNameInputField = new JTextField(15);
    private final JTextField lastNameInputField = new JTextField(15);
    private final SignupStudentController signupController;
    private final JButton signUp;

    public StudentSignupView(SignupStudentController controller, SignupStudentViewModel signupViewModel) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(signupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel(signupViewModel.EMAIL_LABEL), usernameInputField);
        LabelTextPanel firstNameInfo = new LabelTextPanel(
                new JLabel(signupViewModel.FIRSTNAME_LABEL), firstNameInputField);
        LabelTextPanel lastNameInfo = new LabelTextPanel(
                new JLabel(signupViewModel.LASTNAME_LABEL), lastNameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            signupController.execute(signupViewModel.getState().getEmail(),
                                    signupViewModel.getState().getLastName(),
                                    signupViewModel.getState().getFirstName(),
                                    String.valueOf(signupViewModel.getState().getPassword()),
                                    String.valueOf(signupViewModel.getState().getRepeatPassword()));
                        }
                    }
                }
        );

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupStudentState currentState = signupViewModel.getState();
                        currentState.setEmail(usernameInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
                );

        firstNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupStudentState currentState = signupViewModel.getState();
                        currentState.setFirstName(firstNameInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        lastNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupStudentState currentState = signupViewModel.getState();
                        currentState.setLastName(lastNameInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        repeatPasswordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupStudentState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(String.valueOf(repeatPasswordInputField.getPassword()) + e.getKeyChar());
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        passwordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupStudentState currentState = signupViewModel.getState();
                currentState.setPassword(String.valueOf(passwordInputField.getPassword()) + e.getKeyChar());
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(firstNameInfo);
        this.add(lastNameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupStudentState state = (SignupStudentState) evt.getNewValue();
        if (state.getEmailError() != null) {
            JOptionPane.showMessageDialog(this, state.getEmailError());
        }
        else if (state.getFirstNameError() != null){
            JOptionPane.showMessageDialog(this, state.getFirstNameError());
        }
        else if (state.getLastNameError() != null){
            JOptionPane.showMessageDialog(this, state.getLastNameError());
        }
    }
}

