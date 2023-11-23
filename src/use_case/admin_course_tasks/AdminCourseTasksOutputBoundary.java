package use_case.admin_course_tasks;

import entity.Admin;
import use_case.admin_tasks.AdminTasksOutputData;

public interface AdminCourseTasksOutputBoundary {
    void prepareExit(Admin admin);
    void prepareEditTaskView(AdminCourseTasksOutputData input);
}
