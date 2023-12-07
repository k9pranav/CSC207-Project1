package entity;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Day {

    private final Month month;
    private final int dayOfTheMonth;

    private final int dayOfTheWeek;

    private HashMap<String, Task> mapOfTasks = new HashMap<>();

    public Day(Month month, int dayOfTheMonth, int dayOfTheWeek) {
        this.month = month;
        this.dayOfTheMonth = dayOfTheMonth;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public void addTasks(SimpleDateFormat taskName, Task taskObj){
        this.mapOfTasks.put(taskName.format("hh:mm:ss"), taskObj);
    }

    public Task getTask(String time){
        if(mapOfTasks.containsKey(time)){
            return mapOfTasks.get(time);
        } else {
            throw new RuntimeException();
        }
    }

    public int getDayOfTheMonth(){
        return dayOfTheMonth;
    }

    public int getDayOfTheWeek(){
        return dayOfTheWeek;
    }

    public Month getMonth() {
        return month;
    }

    public HashMap<String, Task> getMapOfTasks() {
        return mapOfTasks;
    }

}
