package use_case.edit_course_task;

import data_access.FileStudentDataAccessObject;
import entity.Admin;
import entity.Course;
import entity.CourseTask;
import entity.Student;

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

        CourseTask newTaskAdded = new CourseTask(taskName, taskType, taskDeadline, taskWeight);
        taskCourse.addTask(newTaskAdded);
        loggedIn.setTask(newTaskAdded);

        // deal with course not existing somewhere

        editCourseTaskDataAccessInterface.createTask(taskName, taskType, taskDeadline, taskCourse, taskWeight);
        EditCourseTaskOutputData editCourseTaskOutputData = new EditCourseTaskOutputData(loggedIn, taskName, false);
        editCourseTaskOutputBoundary.prepareSuccessView(editCourseTaskOutputData);

        // add tasks to students in course
        for (Student student: taskCourse.getStudentsEnrolled()) {
            student.setTask(newTaskAdded);
            // need to call DAO somewhere here
        }
        ;
    }

    @Override
    public void executeExit(Admin loggedIn){
        editCourseTaskOutputBoundary.prepareExit(loggedIn);
    }
}
