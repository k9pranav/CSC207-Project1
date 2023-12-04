package interface_adapter.edit_student_task;

import java.text.SimpleDateFormat;

public class EditStudentTaskState {

    private String taskName = "";

    private String taskType = "";

    private SimpleDateFormat deadline;


    public void setError(String errorString) {
    }

    public void setTaskName(String s) {
        this.taskName = s;
    }

    public void setDeadline(String s) {
        SimpleDateFormat date = new SimpleDateFormat(s);
        this.deadline = date;
    }

    public void setTaskType(String s) {
        this.taskType = s;
    }

    public String getTaskName() {

    }

    public String getTaskType() {
        return this.taskType;
    }

    public SimpleDateFormat getDeadline() {
        return this.deadline;
    }


}
