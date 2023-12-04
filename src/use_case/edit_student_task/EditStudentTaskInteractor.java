package use_case.edit_student_task;

import java.text.SimpleDateFormat;

public class EditStudentTaskInteractor implements EditStudentTaskInputBoundary{

    final EditStudentTaskDataAccessInterface editStudentTaskDataAccessInterface;

    final EditStudentTaskOutputBoundary editStudentTaskOutputBoundary;

    public EditStudentTaskInteractor(EditStudentTaskDataAccessInterface editStudentTaskDataAccessInterface,
                                     EditStudentTaskOutputBoundary editStudentTaskOutputBoundary) {
        this.editStudentTaskDataAccessInterface = editStudentTaskDataAccessInterface;
        this.editStudentTaskOutputBoundary = editStudentTaskOutputBoundary;
    }

    @Override
    public void execute(EditStudentTaskDataInputData editStudentTaskDataInputData) {
        String taskName = editStudentTaskDataInputData.getTaskName();
        String taskType = editStudentTaskDataInputData.getTaskType();
        SimpleDateFormat deadline = editStudentTaskDataInputData.getDeadline();
        String studentEmail = editStudentTaskDataInputData.getStudentEmail();


        if (!editStudentTaskDataAccessInterface.studentExists(studentEmail)){
            editStudentTaskOutputBoundary.prepareFailView("Student does not exists");
        } else {
            editStudentTaskDataAccessInterface.addStudentTasks(taskName,
                    taskType, deadline, studentEmail);

            editStudentTaskOutputBoundary.prepareSuccessView(editStudentTaskDataInputData);

        }

    }
}
