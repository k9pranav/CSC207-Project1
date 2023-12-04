package interface_adapter.edit_student_task;

import use_case.edit_student_task.EditStudentTaskDataInputData;
import use_case.edit_student_task.EditStudentTaskInputBoundary;

import java.text.SimpleDateFormat;

public class EditStudentTaskController {

    final EditStudentTaskInputBoundary editStudentTaskInputBoundary;

    public EditStudentTaskController(EditStudentTaskInputBoundary editStudentTaskInputBoundary) {
        this.editStudentTaskInputBoundary = editStudentTaskInputBoundary;
    }

    public void execute(String taskName,
                        String taskType,
                        SimpleDateFormat deadline,
                        String studentemail){

        EditStudentTaskDataInputData editStudentTaskDataInputData = new EditStudentTaskDataInputData(taskName,
                taskType, deadline, studentemail);

        editStudentTaskInputBoundary.execute(editStudentTaskDataInputData);
    }
}
