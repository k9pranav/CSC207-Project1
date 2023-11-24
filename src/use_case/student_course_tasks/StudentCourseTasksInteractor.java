package use_case.student_course_tasks;

import entity.Course;
import entity.CourseTask;
import use_case.student_tasks.StudentTasksOutputData;
import use_case.student_tasks.StudentTasksDataAccessInterface;

import java.text.SimpleDateFormat;

public class StudentCourseTasksInteractor implements StudentCourseTasksInputBoundary{
    final StudentCourseTasksOutputBoundary tasksPresenter;
    final StudentTasksDataAccessInterface tasksDAO;

    public StudentCourseTasksInteractor(StudentTasksDataAccessInterface tasksDAO, StudentCourseTasksOutputBoundary tasksPresenter){
        this.tasksDAO = tasksDAO;
        this.tasksPresenter = tasksPresenter;
    }

    @Override
    public void executeExit(StudentCourseTasksInputData inputData){
        tasksPresenter.prepareExit(inputData.getLoggedIn());
    }

    @Override
    public void executePopup(StudentCourseTasksInputData inputData){
        CourseTask currentTask = (CourseTask) inputData.getLoggedIn().getTaskFromName(inputData.getButtonPressed());
        String name = currentTask.getTaskName();
        SimpleDateFormat deadline = currentTask.getDeadLine();
        Float grade = currentTask.getGrade();
        Float weight = currentTask.getWeight();
        Course course = currentTask.getCourse();
        StudentTasksOutputData outputData = new StudentTasksOutputData(name, deadline, weight, grade, course);
        tasksPresenter.prepareTaskPopup(outputData);
    }
}
