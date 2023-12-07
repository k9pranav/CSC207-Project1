package interface_adapter.student_logged_in;

import data_access.FileStudentDataAccessObject;
import entity.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.login_student.LoginStudentViewModel;
import interface_adapter.signup_student.SignupStudentState;
import interface_adapter.signup_student.SignupStudentViewModel;
import interface_adapter.student_calendar.StudentCalendarState;
import interface_adapter.student_calendar.StudentCalendarViewModel;
import interface_adapter.student_courses.StudentCoursesState;
import interface_adapter.student_courses.StudentCoursesViewModel;
import interface_adapter.student_landing_page.StudentLandingPageViewModel;
import use_case.student_logged_in.StudentLoggedInOutputBoundary;

public class StudentLoggedInPresenter implements StudentLoggedInOutputBoundary {
        private final StudentLoggedInViewModel studentLoggedInViewModel;
        private final StudentCoursesViewModel studentCoursesViewModel;

        private final StudentCalendarViewModel studentCalendarViewModel;
        private ViewManagerModel viewManagerModel;

    public StudentLoggedInPresenter(ViewManagerModel viewManagerModel, StudentLoggedInViewModel studentLoggedInViewModel, StudentCoursesViewModel studentCoursesViewModel, StudentCalendarViewModel studentCalendarViewModel){
            this.viewManagerModel = viewManagerModel;
            this.studentCoursesViewModel = studentCoursesViewModel;
            this.studentLoggedInViewModel = studentLoggedInViewModel;
            this.studentCalendarViewModel = studentCalendarViewModel;
        }

        @Override
        public void prepareCourseLandingPage(FileStudentDataAccessObject studentDataAccessObject){
            StudentLoggedInState loggedInState = studentLoggedInViewModel.getState();
            Student student = studentDataAccessObject.get(loggedInState.getEmail());
            StudentCoursesState coursesState = studentCoursesViewModel.getState();
            coursesState.setLoggedInUser(student);
            studentCoursesViewModel.setState(coursesState);
            studentCoursesViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(studentCoursesViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }

    public void prepareCalendarPage(FileStudentDataAccessObject studentDataAccessObject){
        StudentLoggedInState loggedInState = studentLoggedInViewModel.getState();
        Student student = studentDataAccessObject.get(loggedInState.getEmail());
        StudentCalendarState calendarState = studentCalendarViewModel.getState();

        calendarState.setEmail(student.getEmail());

        studentCalendarViewModel.setState(calendarState);
        studentCalendarViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(studentCalendarViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
