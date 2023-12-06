package interface_adapter.student_logged_in;

import data_access.FileStudentDataAccessObject;
import entity.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.login_student.LoginStudentViewModel;
import interface_adapter.signup_student.SignupStudentState;
import interface_adapter.signup_student.SignupStudentViewModel;
import interface_adapter.student_courses.StudentCoursesState;
import interface_adapter.student_courses.StudentCoursesViewModel;
import interface_adapter.student_landing_page.StudentLandingPageViewModel;
import use_case.student_logged_in.StudentLoggedInOutputBoundary;

public class StudentLoggedInPresenter implements StudentLoggedInOutputBoundary {
        private final StudentLoggedInViewModel studentLoggedInViewModel;
        private final StudentCoursesViewModel studentCoursesViewModel;
        private ViewManagerModel viewManagerModel;

    public StudentLoggedInPresenter(ViewManagerModel viewManagerModel, StudentLoggedInViewModel studentLoggedInViewModel, StudentCoursesViewModel studentCoursesViewModel){
            this.viewManagerModel = viewManagerModel;
            this.studentCoursesViewModel = studentCoursesViewModel;
            this.studentLoggedInViewModel = studentLoggedInViewModel;
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
}
