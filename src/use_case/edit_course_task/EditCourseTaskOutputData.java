package use_case.edit_course_task;

public class EditCourseTaskOutputData {
   private final String taskName;

   private boolean useCaseFailed;

   public EditCourseTaskOutputData(String taskName, boolean useCaseFailed){
       this.taskName = taskName;
       this.useCaseFailed = useCaseFailed;
   }

   public String getTaskName(){return taskName;}

}
