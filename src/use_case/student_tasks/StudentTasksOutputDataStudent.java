package use_case.student_tasks;

import java.text.SimpleDateFormat;

public class StudentTasksOutputDataStudent {
    private String name;
    private String type;
    private SimpleDateFormat deadline;
    public StudentTasksOutputDataStudent(String name, SimpleDateFormat date, String type) {
        this.name = name;
        this.deadline = date;
        this.type = type;
    }
    public String getName(){return name;}
    public String getType(){return type;}

    public SimpleDateFormat getDeadline(){return deadline;}
}
