package interface_adapter.edit_course_task;

import entity.Course;
import use_case.edit_course_task.EditCourseTaskInputBoundary;
import use_case.edit_course_task.EditCourseTaskInputData;
import use_case.login_admin.LoginAdminInputData;

import java.text.SimpleDateFormat;

public class EditCourseTaskController {

    final EditCourseTaskInputBoundary editCourseTaskInputBoundary;


    public EditCourseTaskController(EditCourseTaskInputBoundary editCourseTaskInputBoundary) {
        this.editCourseTaskInputBoundary = editCourseTaskInputBoundary;
    }

    public void execute(String taskName,
                        String taskType,
                        SimpleDateFormat deadline,
                        Float taskWeight,
                        Course taskCourse){
        EditCourseTaskInputData editCourseTaskInputData = new EditCourseTaskInputData(taskName,
                taskType, deadline, taskWeight, taskCourse);

        editCourseTaskInputBoundary.execute(editCourseTaskInputData);

    }
}
