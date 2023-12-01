package use_case.admin_course_tasks;

public interface AdminCourseTasksInputBoundary {
    void execute(AdminCourseTasksInputData input);

    void executePopup(AdminCourseTasksInputData input);
}
