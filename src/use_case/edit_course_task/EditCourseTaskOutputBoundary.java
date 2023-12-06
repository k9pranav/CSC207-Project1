package use_case.edit_course_task;

import entity.Admin;

public interface EditCourseTaskOutputBoundary {
    void prepareSuccessView(EditCourseTaskOutputData courseTask);

    void prepareFailView(String errorstring);

    void prepareExit(Admin loggedIn);

}
