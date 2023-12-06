package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_back_student.GoBackStudentController;
import interface_adapter.go_back_student.GoBackStudentPresenter;
import interface_adapter.go_back_student.GoBackStudentViewModel;
import interface_adapter.loggedin_student.LoggedinStudentViewModel;
import use_case.go_back_student.GoBackStudentDataAccessInterface;
import use_case.go_back_student.GoBackStudentInputBoundary;
import use_case.go_back_student.GoBackStudentInteractor;
import use_case.go_back_student.GoBackStudentOutputBoundary;

import javax.swing.*;
import java.io.IOException;

public class StudentCalendarUseCaseFactory {


    private StudentCalendarUseCaseFactory(){}

    public static StudentCalendarView create(ViewManagerModel viewManagerModel, 
                                          LoggedinStudentViewModel loggedinStudentViewModel,
                                          GoBackStudentViewModel goBackStudentViewModel,
                                          GoBackStudentDataAccessInterface userDataAccessObject){
        try {
            GoBackStudentController goBackStudentController = createGoBackUseCase(viewManagerModel, goBackStudentViewModel, userDataAccessObject,loggedinStudentViewModel);
            return new StudentCalendarView(goBackStudentViewModel,goBackStudentController);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open data file");
        }
        
        return null;
        
    }

    private static GoBackStudentController createGoBackUseCase(
            ViewManagerModel viewManagerModel, GoBackStudentViewModel goBackStudentViewModel, GoBackStudentDataAccessInterface userDataAccessObject, LoggedinStudentViewModel loggedinStudentViewModel){

        GoBackStudentOutputBoundary goBackStudentOutputBoundary= new GoBackStudentPresenter(goBackStudentViewModel, viewManagerModel, loggedinStudentViewModel);

        GoBackStudentInputBoundary goBackStudentInteractor = new GoBackStudentInteractor(userDataAccessObject, goBackStudentOutputBoundary);
        
        return  new GoBackStudentController(goBackStudentInteractor);
    }
}
