package entity;

import java.util.HashMap;

public class Day {

    private final int month;
    private final int dayOfTheMonth;

    private final int dayOfTheWeek;

    private HashMap<String, Task> mapOfTasks = new HashMap<>();

    public Day(int month, int dayOfTheMonth, int dayOfTheWeek) {
        this.month = month;
        this.dayOfTheMonth = dayOfTheMonth;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public void addTasks(String taskName, Task taskObj){
        this.mapOfTasks.put(taskName, taskObj);
    }

    public int getDayOfTheMonth(){
        return dayOfTheMonth;
    }

    public int getDayOfTheWeek(){
        return dayOfTheWeek;
    }

    public int getMonth() {
        return month;
    }

    public HashMap<String, Task> getMapOfTasks() {
        return mapOfTasks;
    }

}
