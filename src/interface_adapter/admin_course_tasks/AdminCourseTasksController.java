package interface_adapter.admin_course_tasks;

import entity.Admin;
import use_case.admin_course_tasks.AdminCourseTasksInputBoundary;
import use_case.admin_course_tasks.AdminCourseTasksInputData;

public class AdminCourseTasksController {
    private final AdminCourseTasksInputBoundary interactor;

    public AdminCourseTasksController(AdminCourseTasksInputBoundary interactor){
        this.interactor = interactor;
    }
    public void execute(String buttonPressed, Admin loggedIn){
        AdminCourseTasksInputData inputData = new AdminCourseTasksInputData(buttonPressed, loggedIn);
        interactor.execute(inputData);
    }
}
