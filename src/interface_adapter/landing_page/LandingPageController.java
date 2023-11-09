package interface_adapter.landing_page;
import use_case.landing_page.LandingPageUseCaseInteractor;

public class LandingPageController {
    private final LandingPageUseCaseInteractor landingPageUseCaseInteractor;

    public LandingPageController(LandingPageUseCaseInteractor landingPageUseCaseInteractor){
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
