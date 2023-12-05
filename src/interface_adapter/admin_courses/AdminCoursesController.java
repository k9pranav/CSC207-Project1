package interface_adapter.admin_courses;
import entity.Admin;
import use_case.admin_courses.AdminCoursesInputBoundary;
import use_case.admin_courses.AdminCoursesInputData;

public class AdminCoursesController {
    private final AdminCoursesInputBoundary interactor;
    public AdminCoursesController(AdminCoursesInputBoundary interactor){
        this.interactor = interactor;
    }
    public void execute(String buttonPressed, Admin loggedIn){
        AdminCoursesInputData inputData = new AdminCoursesInputData(buttonPressed, loggedIn);
        interactor.execute(inputData);
    }
}
