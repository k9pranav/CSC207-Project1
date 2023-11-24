package interface_adapter.student_course_tasks;

import entity.Student;

public class StudentCourseTasksState {
    private Student studentLoggedIn;
    private String currentTaskInfo;

    public StudentCourseTasksState(StudentCourseTasksState copy){studentLoggedIn = copy.studentLoggedIn;}
    public StudentCourseTasksState(){}
    public void setStudentLoggedIn(Student student){this.studentLoggedIn = student;}
    public Student getLoggedInUser(){return this.studentLoggedIn;}
    public void setCurrentTaskInfo(String currentTaskInfo){this.currentTaskInfo = currentTaskInfo;}

    public String getCurrentTaskInfo(){return currentTaskInfo;}
}
