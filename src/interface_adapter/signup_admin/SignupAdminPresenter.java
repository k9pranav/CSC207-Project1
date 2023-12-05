package interface_adapter.signup_admin;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_admin.LoginAdminState;
import interface_adapter.login_admin.LoginAdminViewModel;
import use_case.signup_admin.SignupAdminOutputBoundary;
import use_case.signup_admin.SignupAdminOutputData;
import view.ViewManager;

import entity.Admin;

public class SignupAdminPresenter implements SignupAdminOutputBoundary{

    private final SignupAdminViewModel signupAdminViewModel;

    private final LoginAdminViewModel loginAdminViewModel;
    private ViewManagerModel viewManager;

    public SignupAdminPresenter (SignupAdminViewModel signupAdminViewModel,
                                 ViewManagerModel viewManager, LoginAdminViewModel
                                         loginAdminViewModel){
        this.signupAdminViewModel = signupAdminViewModel;
        this.viewManager = viewManager;
        this.loginAdminViewModel = loginAdminViewModel;
    //TODO add LoginViewModel

    }

    public void prepareSuccessView(SignupAdminOutputData response){
        LoginAdminState loginAdminState = loginAdminViewModel.getState();

        loginAdminState.setEmail(response.getEmail());

        this.loginAdminViewModel.setState(loginAdminState);
        loginAdminViewModel.firePropertyChanged();

        viewManager.setActiveView(loginAdminViewModel.getViewName());

        viewManager.firePropertyChanged();

    }

    public void prepareFailView(String error){
        SignupAdminState signupAdminState = signupAdminViewModel.getState();
        signupAdminState.setError(error);
        signupAdminViewModel.firePropertyChanged();
    }
}
