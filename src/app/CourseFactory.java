package app;

import data_access.CourseDataAccessObject;
import entity.Course;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_landing_page.AdminLandingPageViewModel;
import interface_adapter.landing_page.LandingPageController;
import interface_adapter.landing_page.LandingPageViewModel;
import interface_adapter.student_landing_page.StudentLandingPageViewModel;
import view.LandingPageView;

public class CourseFactory {
    static CourseDataAccessObject courseDataAccessObject = null;
    public static Course load(String course_code) {
        if (courseDataAccessObject == null) {
            courseDataAccessObject = new CourseDataAccessObject("./courses.json");
        }
        return courseDataAccessObject.get(course_code);
    }
}
