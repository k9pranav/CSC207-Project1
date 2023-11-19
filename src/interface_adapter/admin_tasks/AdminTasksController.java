package interface_adapter.admin_tasks;

import entity.Admin;
import use_case.admin_tasks.AdminTasksInputBoundary;
import use_case.admin_tasks.AdminTasksInputData;
import use_case.student_tasks.StudentTasksInputData;

public class AdminTasksController {
    private final AdminTasksInputBoundary interactor;

    public AdminTasksController(AdminTasksInputBoundary interactor){
        this.interactor = interactor;
    }

    public void execute(String buttonPressed, Admin loggedIn){
        AdminTasksInputData inputData = new AdminTasksInputData(buttonPressed, loggedIn);
        interactor.execute(inputData);
    }
}
