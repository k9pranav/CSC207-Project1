package interface_adapter.landing_page;
import use_case.landing_page.LandingPageInputBoundary;
import use_case.landing_page.LandingPageUseCaseInteractor;

public class LandingPageController {
    private final LandingPageInputBoundary landingPageUseCaseInteractor;

    public LandingPageController(LandingPageInputBoundary landingPageUseCaseInteractor){
        this.landingPageUseCaseInteractor = landingPageUseCaseInteractor;
    }
    public void execute(String userType){
        if ("student".equals(userType)){
            landingPageUseCaseInteractor.execute("student");
        }
        else if ("admin".equals(userType)){
            landingPageUseCaseInteractor.execute("admin");
        }

    }
}
