package use_case.student_tasks;

import java.text.SimpleDateFormat;

public class StudentTasksOutputData {
    private String name;
    private SimpleDateFormat deadline;

    public StudentTasksOutputData(String name, SimpleDateFormat date){
        this.name = name;
        this.deadline = date;
    }

    public String getName(){
        return this.name;
    }

    public SimpleDateFormat getDeadline(){
        return this.deadline;
    }
}
