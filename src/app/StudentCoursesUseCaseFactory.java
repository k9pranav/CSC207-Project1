package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_student.LoggedinStudentViewModel;
import interface_adapter.student_course_tasks.StudentCourseTasksViewModel;
import interface_adapter.student_courses.StudentCoursesController;
import interface_adapter.student_courses.StudentCoursesPresenter;
import interface_adapter.student_courses.StudentCoursesViewModel;
import interface_adapter.student_logged_in.StudentLoggedInController;
import interface_adapter.student_logged_in.StudentLoggedInPresenter;
import interface_adapter.student_logged_in.StudentLoggedInViewModel;
import use_case.student_courses.StudentCoursesInputBoundary;
import use_case.student_courses.StudentCoursesInteractor;
import use_case.student_logged_in.StudentLoggedInInputBoundary;
import use_case.student_logged_in.StudentLoggedInOutputBoundary;
import use_case.student_logged_in.StudentLoggedinInteractor;
import view.StudentCoursesView;
import view.StudentLoggedInView;

public class StudentCoursesUseCaseFactory {
    public static StudentCoursesView create(
            ViewManagerModel viewManagerModel,
            StudentCoursesViewModel studentCoursesViewModel,
            StudentCourseTasksViewModel studentCourseTasksViewModel,
            StudentLoggedInViewModel homePageViewMode) {
        StudentCoursesController studentCoursesController = createStudentCoursesPageUseCase(
                viewManagerModel,
                studentCoursesViewModel,
                studentCourseTasksViewModel,
                homePageViewMode);
        return new StudentCoursesView(studentCoursesViewModel, studentCoursesController);
    }

    private static StudentCoursesController createStudentCoursesPageUseCase(
            ViewManagerModel viewManagerModel,
            StudentCoursesViewModel studentCoursesViewModel,
            StudentCourseTasksViewModel studentCourseTasksViewModel,
            StudentLoggedInViewModel homePageViewModel) {
        StudentCoursesPresenter studentCoursesOutputBoundary = new StudentCoursesPresenter(
                viewManagerModel,
                studentCoursesViewModel,
                studentCourseTasksViewModel,
                homePageViewModel );
        // TODO manage the data interface
        StudentCoursesInputBoundary interactor = new StudentCoursesInteractor(null ,studentCoursesOutputBoundary);
        return new StudentCoursesController(interactor);
    }
}
