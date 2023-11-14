package use_case.landing_page;

public class LandingPageUseCaseInteractor implements LandingPageInputBoundary{
    final LandingPageOutputBoundary landingPresenter;

    public LandingPageUseCaseInteractor(LandingPageOutputBoundary landingPresenter){
        this.landingPresenter = landingPresenter;
    }
    @Override
    public void execute(String userType){
        if ("student".equals(userType)){
            landingPresenter.prepareStudentLandingPage();
        }
        else if ("admin".equals(userType)){
            landingPresenter.prepareAdminLandingPage();
        }
    }
}
