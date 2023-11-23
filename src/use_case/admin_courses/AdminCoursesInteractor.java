package use_case.admin_courses;

import entity.Course;
import entity.CourseTask;
import java.util.ArrayList;

public class AdminCoursesInteractor implements AdminCoursesInputBoundary {
    final AdminCoursesDataAccessInterface coursesDAO;
    final AdminCoursesOutputBoundary coursesPresenter;

    public AdminCoursesInteractor(AdminCoursesDataAccessInterface coursesDAO, AdminCoursesOutputBoundary coursesPresenter){
        this.coursesDAO = coursesDAO;
        this.coursesPresenter = coursesPresenter;
    }

    @Override
    public void execute(AdminCoursesInputData input){
        // check which of the three buttons was pressed
        String button = input.getButton();
        if (("exit").equals(button)){
            coursesPresenter.prepareExit(input.getLoggedIn());
            // exit button pressed
        } else {
            // its equal to "Course Code: course name"
            // open tasks view for the courses' tasks
            String courseCode = input.getButton();
            ArrayList<CourseTask> tasks = input.getLoggedIn().getTasksForCourse(courseCode);
            Course currentCourse = input.getLoggedIn().getCourseFromCourseCode(courseCode);
            AdminCoursesOutputData outputData = new AdminCoursesOutputData(tasks, input.getLoggedIn(), currentCourse);
            coursesPresenter.prepareCourseTasksView(outputData);
        }
    }
}
