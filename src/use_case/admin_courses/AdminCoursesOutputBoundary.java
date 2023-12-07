package use_case.admin_courses;

import entity.Admin;

public interface AdminCoursesOutputBoundary {
    void prepareExit(Admin admin);
    void prepareCourseTasksView(AdminCoursesOutputData outputData);
}
