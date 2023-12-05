package interface_adapter.student_tasks;

import entity.Student;

import java.util.ArrayList;

public class StudentTasksState {
    private Student studentLoggedIn;
    private String currentTaskInfo;

    public StudentTasksState(StudentTasksState copy){
        studentLoggedIn = copy.studentLoggedIn;
    }
    public StudentTasksState(){}

    public void setStudentLoggedIn(Student student){
        this.studentLoggedIn = student;
    }
    public Student getLoggedInUser() {
        return this.studentLoggedIn;
    }
    public void setCurrentTaskInfo(String currentTask){
        this.currentTaskInfo = currentTask;
    }

    public String getCurrentTaskInfo(){
        return currentTaskInfo;
    }
}
