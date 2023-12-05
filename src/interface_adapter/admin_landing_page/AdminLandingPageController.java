package interface_adapter.admin_landing_page;

import use_case.admin_landing_page.AdminLandingPageInputBoundary;
import use_case.admin_landing_page.AdminLandingPageUseCaseInteractor;

public class AdminLandingPageController {
    private final AdminLandingPageInputBoundary adminLandingInteractor;
    public AdminLandingPageController(AdminLandingPageInputBoundary adminLandingInteractor){
        this.adminLandingInteractor = adminLandingInteractor;

    }
    public void execute(String buttonPressed){
        if ("signup".equals(buttonPressed)){
            adminLandingInteractor.execute("signup");
        }
        else if ("login".equals(buttonPressed)){
            adminLandingInteractor.execute("login");
        }

    }
}
