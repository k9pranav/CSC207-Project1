package interface_adapter.admin_landing_page;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup_admin.SignupAdminState;
import interface_adapter.signup_admin.SignupAdminViewModel;
import use_case.admin_landing_page.AdminLandingPageOutputBoundary;

public class AdminLandingPagePresenter implements AdminLandingPageOutputBoundary {
    private final AdminLandingPageViewModel adminLandingPageViewModel;
    private final SignupAdminViewModel signupViewModel;
    private final LoginAdminViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public AdminLandingPagePresenter(ViewManagerModel viewManagerModel, AdminLandingPageViewModel adminLandingPageViewModel, SignupAdminViewModel signupViewModel, LoginAdminViewModel loginViewModel){
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.adminLandingPageViewModel = adminLandingPageViewModel;
    }

    @Override
    public void prepareSignupLandingPage(){
        SignupAdminState signupState = signupViewModel.getState();
        this.signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareLoginLandingPage(){
        SignupAdminState loginState = loginViewModel.getState();
        this.loginViewModel.setState(signupState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
