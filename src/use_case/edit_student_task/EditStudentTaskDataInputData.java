package use_case.edit_student_task;

import java.text.SimpleDateFormat;

public class EditStudentTaskDataInputData {

    private final String taskName;

    private final String taskType;

    private final String studentEmail;
    private final SimpleDateFormat deadline;


    public EditStudentTaskDataInputData(String taskName,
                                        String taskType,
                                        SimpleDateFormat deadline,
                                        String studentEmail) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.deadline = deadline;
        this.studentEmail = studentEmail;

    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public SimpleDateFormat getDeadline() {
        return deadline;
    }

    public String getStudentEmail(){
        return  studentEmail;
    }
}
