package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.admin_landing_page.AdminLandingPageController;
import interface_adapter.admin_landing_page.AdminLandingPagePresenter;
import interface_adapter.admin_landing_page.AdminLandingPageViewModel;
import interface_adapter.landing_page.LandingPageController;
import interface_adapter.landing_page.LandingPagePresenter;
import interface_adapter.landing_page.LandingPageViewModel;
import interface_adapter.login_admin.LoginAdminViewModel;
import interface_adapter.signup_admin.SignupAdminViewModel;
import interface_adapter.student_landing_page.StudentLandingPageViewModel;
import use_case.admin_landing_page.AdminLandingPageInputBoundary;
import use_case.admin_landing_page.AdminLandingPageOutputBoundary;
import use_case.admin_landing_page.AdminLandingPageUseCaseInteractor;
import use_case.landing_page.LandingPageInputBoundary;
import use_case.landing_page.LandingPageOutputBoundary;
import use_case.landing_page.LandingPageUseCaseInteractor;
import view.AdminLandingPageView;
import view.LandingPageView;

public class AdminLandingPageUseCaseFactory {
    public static AdminLandingPageView create(ViewManagerModel viewManagerModel, AdminLandingPageViewModel adminLandingPageViewModel, SignupAdminViewModel adminSignupViewModel, LoginAdminViewModel adminLoginViewModel) {
        AdminLandingPageController adminLandingPageController = createAdminLandingPageUseCase(viewManagerModel, adminLandingPageViewModel, adminLoginViewModel, adminSignupViewModel);
        return new AdminLandingPageView(adminLandingPageController, adminLandingPageViewModel);
    }

    private static AdminLandingPageController createAdminLandingPageUseCase(ViewManagerModel viewManagerModel, AdminLandingPageViewModel adminLandingPageViewModel, LoginAdminViewModel loginAdminViewModel, SignupAdminViewModel signupAdminViewModel) {
        AdminLandingPageOutputBoundary adminLandingPageOutputBoundary = new AdminLandingPagePresenter(viewManagerModel, adminLandingPageViewModel, signupAdminViewModel, loginAdminViewModel);

        AdminLandingPageInputBoundary landingPageInteractor = new AdminLandingPageUseCaseInteractor(adminLandingPageOutputBoundary);
        return new AdminLandingPageController(landingPageInteractor);

    }
}

