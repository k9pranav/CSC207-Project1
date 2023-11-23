package use_case.student_courses;

import entity.Course;
import entity.CourseTask;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class StudentCoursesInteractor implements StudentCoursesInputBoundary{
    final StudentCoursesDataAccessInterface coursesDAO;
    final StudentCoursesOutputBoundary coursesPresenter;

    public StudentCoursesInteractor(StudentCoursesDataAccessInterface coursesDAO, StudentCoursesOutputBoundary coursesPresenter){
        this.coursesDAO = coursesDAO;
        this.coursesPresenter = coursesPresenter;
    }

    @Override
    public void execute(StudentCoursesInputData input){
        // check which of the three buttons was pressed
        String button = input.getButton();
        if (("exit").equals(button)){
            coursesPresenter.prepareExit(input.getLoggedIn());
            // exit button pressed
        } else if (("get average").equals(button)){
            // get average button pressed
            float total = 0.0f;
            int count = 0;
            HashMap<String, Integer> courseGrades = input.getLoggedIn().getStudentGrades();
            for (final String key : courseGrades.keySet()) {
                count = count + 1;
                total = total + courseGrades.get(key);
            }
            float avg = (total / count);
            coursesPresenter.prepareAverage(avg, input.getLoggedIn());
        } else {
            // its equal to "Course Code: course name"
            // open tasks view for the courses' tasks
            String[] courseCodeAndName = input.getButton().split(":");
            String courseCode = courseCodeAndName[0];
            ArrayList<CourseTask> tasks = input.getLoggedIn().getTasksForCourse(courseCode);
            StudentCoursesOutputData outputData = new StudentCoursesOutputData(tasks, input.getLoggedIn());
            coursesPresenter.prepareCourseTasksView(outputData);
        }
    }
}
