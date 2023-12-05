package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.admin_landing_page.AdminLandingPageViewModel;
import interface_adapter.landing_page.LandingPageController;
import interface_adapter.landing_page.LandingPagePresenter;
import interface_adapter.landing_page.LandingPageViewModel;
import interface_adapter.student_landing_page.StudentLandingPageViewModel;
import use_case.landing_page.LandingPageInputBoundary;
import use_case.landing_page.LandingPageOutputBoundary;
import use_case.landing_page.LandingPageUseCaseInteractor;
import view.LandingPageView;

public class LandingPageUseCaseFactory {
    public static LandingPageView create(ViewManagerModel viewManagerModel, LandingPageViewModel landingPageViewModel, StudentLandingPageViewModel studentLandingPageViewModel, AdminLandingPageViewModel adminLandingPageViewModel) {
        LandingPageController landingPageController = createLandingPageUseCase(viewManagerModel, landingPageViewModel, studentLandingPageViewModel, adminLandingPageViewModel);
        return new LandingPageView(landingPageController, landingPageViewModel);
    }
    private static LandingPageController createLandingPageUseCase(ViewManagerModel viewManagerModel, LandingPageViewModel landingPageViewModel, StudentLandingPageViewModel studentLandingPageViewModel, AdminLandingPageViewModel adminLandingPageViewModel){
        LandingPageOutputBoundary landingPageOutputBoundary = new LandingPagePresenter(viewManagerModel, landingPageViewModel, studentLandingPageViewModel, adminLandingPageViewModel);

        LandingPageInputBoundary landingPageInteractor = new LandingPageUseCaseInteractor(landingPageOutputBoundary);
        return new LandingPageController(landingPageInteractor);
        }
    }

