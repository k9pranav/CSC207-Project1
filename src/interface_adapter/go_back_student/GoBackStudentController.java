package interface_adapter.go_back_student;


import use_case.go_back_student.GoBackStudentInputBoundary;
import use_case.go_back_student.GoBackStudentInputData;

public class GoBackStudentController {

    final GoBackStudentInputBoundary goBackUseCaseInteractor;


    public GoBackStudentController(GoBackStudentInputBoundary goBackUseCaseInteractor) {
        this.goBackUseCaseInteractor = goBackUseCaseInteractor;
    }

    public void execute(String email, String accountType){
        GoBackStudentInputData goBackInputData = new GoBackStudentInputData(email, accountType);

        goBackUseCaseInteractor.execute(goBackInputData);
    }
}
