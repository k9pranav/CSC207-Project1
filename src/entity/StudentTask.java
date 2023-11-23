package entity;

import java.text.SimpleDateFormat;

public class StudentTask extends Task {

    public StudentTask(String taskName, String type, SimpleDateFormat deadLine) {
        super(taskName, type, deadLine);
    }

    public void editTaskName(String name){
        this.taskName = name;
    }

}
