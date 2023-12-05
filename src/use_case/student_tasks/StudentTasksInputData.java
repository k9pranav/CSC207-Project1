package use_case.student_tasks;

import entity.Student;

public class StudentTasksInputData {
    final private String taskName;
    final private Student loggedIn;

    public StudentTasksInputData(String taskName, Student loggedIn){

        this.taskName = taskName;
        this.loggedIn = loggedIn;
    }

    public String getTaskName(){return taskName;}

    public Student getLoggedIn(){return loggedIn;}
}
