package use_case.edit_student_task;

import java.text.SimpleDateFormat;

public interface EditStudentTaskDataAccessInterface {

    boolean studentExists(String studentEmail);

    void addStudentTasks(String taskName,
                         String taskType,
                         SimpleDateFormat deadline,
                         String studentEmail);

}
