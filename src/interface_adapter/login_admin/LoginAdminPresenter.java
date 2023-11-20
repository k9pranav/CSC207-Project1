package interface_adapter.login_admin;

import interface_adapter.ViewManagerModel;
import interface_adapter.admin_landing_page.AdminLandingPageViewModel;
import interface_adapter.landing_page.LandingPageState;
import interface_adapter.landing_page.LandingPageViewModel;
import interface_adapter.signup_admin.SignupAdminViewModel;
import use_case.login_admin.LoginAdminOutputBoundary;
import use_case.login_admin.LoginAdminOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginAdminPresenter implements LoginAdminOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoginAdminViewModel viewModel;
    private final LandingPageViewModel landingViewModel;

    public LoginAdminPresenter(ViewManagerModel viewManagerModel, LoginAdminViewModel viewModel, LandingPageViewModel landingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
        this.landingViewModel = landingViewModel;
    }

    @Override
    public void prepareSuccessView(LoginAdminOutputData outputData){
            // On success, switch to the logged in view
            // still need to figure out how to implement logged in view, and what info is necessary
            // when switching to the view
            LoginAdminState loginState = viewModel.getState();
            LoggedInState loggedInState = loggedInViewModel.getState();
            this.loggedInViewModel.setState(loggedInState);
            loggedInViewModel.firePropertyChanged();
            viewManagerModel.setActiveView(loggedInView.getViewName());
            viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView(String error){
        LoginAdminState loginState = viewModel.getState();
        loginState.setUsernameError(error);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareCancelView(){
        LandingPageState landingState = landingViewModel.getState();
        this.landingViewModel.setState(landingState);
        landingViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(landingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
