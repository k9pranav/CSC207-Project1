package app;

import data_access.FileStudentDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.student_calendar.StudentCalendarViewModel;
import interface_adapter.student_courses.StudentCoursesViewModel;
import interface_adapter.student_logged_in.StudentLoggedInController;
import interface_adapter.student_logged_in.StudentLoggedInPresenter;
import interface_adapter.student_logged_in.StudentLoggedInViewModel;
import use_case.student_logged_in.StudentLoggedInInputBoundary;
import use_case.student_logged_in.StudentLoggedInOutputBoundary;
import use_case.student_logged_in.StudentLoggedinInteractor;
import view.StudentLoggedInView;

public class StudentLoggedInUseCaseFactory {

    public static StudentLoggedInView create(
            ViewManagerModel viewManagerModel,
            StudentLoggedInViewModel studentLoggedInViewModel,
            StudentCoursesViewModel studentCoursesViewModel,
            StudentCalendarViewModel studentCalendarViewModel,
            FileStudentDataAccessObject fileStudentDataAccessObject) {


    StudentLoggedInController studentLoggedInController = createStudentLoggedInPageUseCase(
            viewManagerModel,
            studentLoggedInViewModel,
            studentCoursesViewModel,
            studentCalendarViewModel,
            fileStudentDataAccessObject);


    return new StudentLoggedInView(studentLoggedInController, studentLoggedInViewModel);
    }

        private static StudentLoggedInController createStudentLoggedInPageUseCase(
                ViewManagerModel viewManagerModel,
                StudentLoggedInViewModel studentLoggedInViewModel,
                StudentCoursesViewModel studentCoursesViewModel,
                StudentCalendarViewModel studentCalendarViewModel,
                FileStudentDataAccessObject fileStudentDataAccessObject
                ) {
            StudentLoggedInOutputBoundary studentLoggedInOutputBoundary = new StudentLoggedInPresenter(viewManagerModel, studentLoggedInViewModel, studentCoursesViewModel, studentCalendarViewModel);

            StudentLoggedInInputBoundary interactor = new StudentLoggedinInteractor(studentLoggedInOutputBoundary, fileStudentDataAccessObject);
            return new StudentLoggedInController(interactor);
        }
}
