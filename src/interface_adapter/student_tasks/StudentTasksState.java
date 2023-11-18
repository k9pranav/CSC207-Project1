package interface_adapter.student_tasks;

import entity.Student;

public class StudentTasksState {
    private Student studentLoggedIn;

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
}
