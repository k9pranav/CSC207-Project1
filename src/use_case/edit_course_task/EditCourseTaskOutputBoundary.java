package use_case.edit_course_task;

public interface EditCourseTaskOutputBoundary {
    void prepareSuccessView(EditCourseTaskOutputData courseTask);

    void prepareFailView(String errorstring);


}
