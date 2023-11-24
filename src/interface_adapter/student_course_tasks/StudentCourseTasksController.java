package interface_adapter.student_course_tasks;

import use_case.student_course_tasks.StudentCourseTasksInputBoundary;
import use_case.student_course_tasks.StudentCourseTasksInputData;

public class StudentCourseTasksController {
    private final StudentCourseTasksInputBoundary interactor;

    public StudentCourseTasksController(StudentCourseTasksInputBoundary interactor){
        this.interactor = interactor;
    }

    public void execute(String buttonPressed, Student loggedIn){
        StudentCourseTasksInputData inputData = new StudentCourseTasksInputData(buttonPressed, loggedIn);
        if (("exit").equals(buttonPressed)){
            interactor.executeExit(inputData);
        }else {
            interactor.executePopup(inputData);
            //double click
        }
    }
}
