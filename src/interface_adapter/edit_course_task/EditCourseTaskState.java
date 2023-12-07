package interface_adapter.edit_course_task;

public class EditCourseTaskState {

    private String taskName = "";
    private String taskType = "";
    private String errorString = null;


    public void setTaskName(String s) {
    }

    public void setTaskCourseName(String s) {
    }

    public void setTaskType(String s) {
    }

    public void setTaskDeadline(String s) {
    }

    public void setTaskWeight(float v) {
    }

    public void setError(String errorstring) {
        this.errorString = errorstring;
    }
}
