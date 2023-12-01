package use_case.edit_student_task;

public class EditStudentTaskOutputData {
    private final String taskName;

    private boolean taskCreatedFailed;


    public EditStudentTaskOutputData(String taskName, boolean taskCreatedFailed) {
        this.taskName = taskName;
        this.taskCreatedFailed = taskCreatedFailed;
    }

    public String getTaskName(){return taskName;}
}
