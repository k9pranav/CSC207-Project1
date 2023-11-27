package use_case.admin_tasks;

import entity.Admin;

public interface AdminTasksOutputBoundary {
    void prepareExit(Admin admin);
    void prepareEditTaskView(AdminTasksOutputData input);
}
