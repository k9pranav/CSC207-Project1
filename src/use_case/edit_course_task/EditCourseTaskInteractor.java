package use_case.edit_course_task;

import entity.Course;

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
    public void execute(EditCourseTaskInputData editCourseTaskInputData) {
        String taskName = editCourseTaskInputData.getTaskName();
        String taskType = editCourseTaskInputData.getTaskType();
        SimpleDateFormat taskDeadline = editCourseTaskInputData.getDeadine();
        float taskWeight = editCourseTaskInputData.getTaskWeight();
        Course taskCourse = editCourseTaskInputData.getTaskCourse();

        if (!editCourseTaskDataAccessInterface.doesAdminHaveCourse(taskCourse)){
            editCourseTaskOutputBoundary.prepareFailView("Course not there");
        } else {
            editCourseTaskDataAccessInterface.createTask(taskName, taskType, taskDeadline, taskCourse,
                    taskWeight);
            EditCourseTaskOutputData editCourseTaskOutputData = new EditCourseTaskOutputData(taskName,
                    false);
            editCourseTaskOutputBoundary.prepareSuccessView(editCourseTaskOutputData);

        }






    }
}
