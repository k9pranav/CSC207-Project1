package use_case.edit_course_task;

import entity.Admin;
import entity.Course;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;

public class EditCourseTaskInteractor implements EditCourseTaskInputBoundary{

    final EditCourseTaskDataAccessInterface editCourseTaskDataAccessInterface;

    final EditCourseTaskOutputBoundary editCourseTaskOutputBoundary;

    public EditCourseTaskInteractor(EditCourseTaskDataAccessInterface editCourseTaskDataAccessInterface,
                                    EditCourseTaskOutputBoundary editCourseTaskOutputBoundary) {
        this.editCourseTaskDataAccessInterface = editCourseTaskDataAccessInterface;
        this.editCourseTaskOutputBoundary = editCourseTaskOutputBoundary;
    }

    @Override
    public void execute(EditCourseTaskInputData editCourseTaskInputData) throws GeneralSecurityException, IOException {
        String taskName = editCourseTaskInputData.getTaskName();
        String taskType = editCourseTaskInputData.getTaskType();
        SimpleDateFormat taskDeadline = editCourseTaskInputData.getDeadline();
        float taskWeight = editCourseTaskInputData.getTaskWeight();
        Course taskCourse = editCourseTaskInputData.getTaskCourse();
        Admin loggedIn = editCourseTaskInputData.getLoggedIn();

        // deal with course not existing somewhere

        editCourseTaskDataAccessInterface.createTask(taskName, taskType, taskDeadline, taskCourse, taskWeight);
        EditCourseTaskOutputData editCourseTaskOutputData = new EditCourseTaskOutputData(loggedIn, taskName, false);
        editCourseTaskOutputBoundary.prepareSuccessView(editCourseTaskOutputData);
    }

    @Override
    public void executeExit(Admin loggedIn){
        editCourseTaskOutputBoundary.prepareExit(loggedIn);
    }
}
