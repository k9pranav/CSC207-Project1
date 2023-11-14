package use_case.admin_landing_page;

import use_case.landing_page.LandingPageOutputBoundary;

public class AdminLandingPageUseCaseInteractor implements AdminLandingPageInputBoundary {
    final AdminLandingPageOutputBoundary adminLandingPresenter;

    public AdminLandingPageUseCaseInteractor(AdminLandingPageOutputBoundary adminLandingPresenter){
        this.adminLandingPresenter = adminLandingPresenter;
    }
    public void execute(String buttonPressed){
        if ("signup".equals(buttonPressed)){
            adminLandingPresenter.prepareSignupLandingPage();
        }
        else if ("login".equals(buttonPressed)){
            adminLandingPresenter.prepareLoginLandingPage();
        }
    }
}
