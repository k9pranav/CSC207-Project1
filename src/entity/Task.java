package entity;

import java.text.SimpleDateFormat;

abstract class Task {
    String taskName;

    String type;

    SimpleDateFormat deadLine;


    public Task(String taskName, String type, SimpleDateFormat deadLine) {
        this.taskName = taskName;
        this.type = type;
        this.deadLine = deadLine;
    }


    public SimpleDateFormat getDeadLine() {return deadLine;}



}
