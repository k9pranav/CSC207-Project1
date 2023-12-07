package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.edit_student_task.EditStudentTaskViewModel;
import interface_adapter.go_back_student.GoBackStudentController;
import interface_adapter.go_back_student.GoBackStudentPresenter;
import interface_adapter.go_back_student.GoBackStudentViewModel;
import interface_adapter.loggedin_student.LoggedinStudentViewModel;
import interface_adapter.student_tasks.StudentTasksController;
import interface_adapter.student_tasks.StudentTasksPresenter;
import interface_adapter.student_tasks.StudentTasksViewModel;
import use_case.go_back_student.GoBackStudentDataAccessInterface;
import use_case.go_back_student.GoBackStudentInputBoundary;
import use_case.go_back_student.GoBackStudentInteractor;
import use_case.go_back_student.GoBackStudentOutputBoundary;
import use_case.student_tasks.StudentTasksDataAccessInterface;
import use_case.student_tasks.StudentTasksInputBoundary;
import use_case.student_tasks.StudentTasksInteractor;
import use_case.student_tasks.StudentTasksOutputBoundary;
import view.StudentCalendarView;

import javax.swing.*;
import java.io.IOException;

public class StudentCalendarUseCaseFactory {


    private StudentCalendarUseCaseFactory(){}

    public static StudentCalendarView create(ViewManagerModel viewManagerModel, LoggedinStudentViewModel loggedinStudentViewModel, GoBackStudentViewModel goBackStudentViewModel,GoBackStudentDataAccessInterface userDataAccessObject, StudentTasksDataAccessInterface studentTasksDataAccessInterface, StudentTasksViewModel studentTasksViewModel, LoggedinStudentViewModel homePageViewModel,EditStudentTaskViewModel editStudentTaskViewModel){
        GoBackStudentController goBackStudentController = createGoBackUseCase(viewManagerModel, goBackStudentViewModel, userDataAccessObject,loggedinStudentViewModel);

        StudentTasksController studentTasksController = createStudentTaskController(viewManagerModel, studentTasksViewModel,  studentTasksDataAccessInterface,  homePageViewModel, editStudentTaskViewModel);

        return new StudentCalendarView(goBackStudentController, goBackStudentViewModel, studentTasksViewModel, studentTasksController);

    }

    private static GoBackStudentController createGoBackUseCase(
            ViewManagerModel viewManagerModel, GoBackStudentViewModel goBackStudentViewModel, GoBackStudentDataAccessInterface userDataAccessObject, LoggedinStudentViewModel loggedinStudentViewModel){

        GoBackStudentOutputBoundary goBackStudentOutputBoundary= new GoBackStudentPresenter(goBackStudentViewModel, viewManagerModel, loggedinStudentViewModel);

        GoBackStudentInputBoundary goBackStudentInteractor = new GoBackStudentInteractor(userDataAccessObject, goBackStudentOutputBoundary);
        
        return  new GoBackStudentController(goBackStudentInteractor);
    }

    private static StudentTasksController createStudentTaskController(
            ViewManagerModel viewManagerModel, StudentTasksViewModel studentTasksViewModel, StudentTasksDataAccessInterface userDataAccessObject, LoggedinStudentViewModel homePageViewModel, EditStudentTaskViewModel editStudentTaskViewModel) {

        StudentTasksOutputBoundary studentTasksOutputBoundary = new StudentTasksPresenter(viewManagerModel, studentTasksViewModel, homePageViewModel, editStudentTaskViewModel);

        StudentTasksInputBoundary studentTasksInteractor = new StudentTasksInteractor(userDataAccessObject, studentTasksOutputBoundary);

        return new StudentTasksController(studentTasksInteractor);

    }
}
