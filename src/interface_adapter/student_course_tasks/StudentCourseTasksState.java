package interface_adapter.student_course_tasks;

import entity.Course;
import entity.CourseTask;
import entity.Student;
import entity.Task;

import java.util.ArrayList;

public class StudentCourseTasksState {
    private Student studentLoggedIn;
    private String currentTaskInfo;

    private ArrayList<CourseTask> tasks;
    private Course currentCourse;

    public StudentCourseTasksState(StudentCourseTasksState copy){studentLoggedIn = copy.studentLoggedIn;}
    public StudentCourseTasksState(){}
    public void setStudentLoggedIn(Student student){this.studentLoggedIn = student;}
    public Student getLoggedInUser(){return this.studentLoggedIn;}
    public void setCurrentTaskInfo(String currentTaskInfo){this.currentTaskInfo = currentTaskInfo;}

    public String getCurrentTaskInfo(){return currentTaskInfo;}

    public void setCurrentCourse(Course course){this.currentCourse = course;}
    public Course getCurrentCourse(){return this.currentCourse;}
    public void setTasks(ArrayList<CourseTask> tasks){this.tasks = tasks;}
    public ArrayList<CourseTask> getTasks(){return tasks;}
}
