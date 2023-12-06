package use_case.edit_course_task;

import entity.Admin;

public class EditCourseTaskOutputData {
   private final String taskName;

   private boolean useCaseFailed;
   private Admin loggedIn;

   public EditCourseTaskOutputData(Admin loggedIn, String taskName, boolean useCaseFailed){
       this.loggedIn = loggedIn;
       this.taskName = taskName;
       this.useCaseFailed = useCaseFailed;
   }

   public String getTaskName(){return taskName;}
    public Admin getLoggedIn(){return loggedIn;}
}
