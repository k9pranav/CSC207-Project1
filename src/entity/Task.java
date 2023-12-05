package entity;

import java.text.SimpleDateFormat;

public abstract class Task {
    String taskName;

    String type;

    SimpleDateFormat deadLine;


    public Task(String taskName, String type, SimpleDateFormat deadLine) {
        this.taskName = taskName;
        this.type = type;
        this.deadLine = deadLine;
    }

    public String getTaskName(){return this.taskName;}
    public SimpleDateFormat getDeadLine() {return deadLine;}
}
