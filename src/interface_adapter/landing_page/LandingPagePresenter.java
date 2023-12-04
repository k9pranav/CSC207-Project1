package interface_adapter.landing_page;

import interface_adapter.ViewManagerModel;
import interface_adapter.admin_landing_page.AdminLandingPageState;
import interface_adapter.admin_landing_page.AdminLandingPageViewModel;
import interface_adapter.student_landing_page.StudentLandingPageState;
import interface_adapter.student_landing_page.StudentLandingPageViewModel;
import use_case.landing_page.LandingPageOutputBoundary;

public class LandingPagePresenter implements LandingPageOutputBoundary {
    private final LandingPageViewModel landingPageViewModel;
    private final StudentLandingPageViewModel studentLandingPageViewModel;
    private final AdminLandingPageViewModel adminLandingPageViewModel;
    private ViewManagerModel viewManagerModel;

    public LandingPagePresenter(ViewManagerModel viewManagerModel, LandingPageViewModel landingPageViewModel, StudentLandingPageViewModel studentLandingPageViewModel, AdminLandingPageViewModel adminLandingPageViewModel){
        this.viewManagerModel = viewManagerModel;
        this.landingPageViewModel = landingPageViewModel;
        this.studentLandingPageViewModel = studentLandingPageViewModel;
        this.adminLandingPageViewModel = adminLandingPageViewModel;
    }

    @Override
    public void prepareStudentLandingPage(){
        StudentLandingPageState studentState = studentLandingPageViewModel.getState();
        this.studentLandingPageViewModel.setState(studentState);
        studentLandingPageViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(studentLandingPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareAdminLandingPage(){
        AdminLandingPageState adminState = adminLandingPageViewModel.getState();
        this.adminLandingPageViewModel.setState(adminState);
        adminLandingPageViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(adminLandingPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
