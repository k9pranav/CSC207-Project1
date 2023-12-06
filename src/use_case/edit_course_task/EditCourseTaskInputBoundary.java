package use_case.edit_course_task;

import entity.Admin;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface EditCourseTaskInputBoundary {
    void execute(EditCourseTaskInputData editCourseTaskInputData) throws GeneralSecurityException, IOException;

    void executeExit(Admin loggedIn);
}
