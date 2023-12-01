package use_case.edit_student_task;

public interface EditStudentTaskOutputBoundary {

    void prepareSuccessView(EditStudentTaskDataInputData editStudentTaskDataInputData);


    void prepareFailView(String errorString);
}
