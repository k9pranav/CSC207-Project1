package use_case.student_tasks;

import entity.Course;
import entity.CourseTask;
import entity.StudentTask;
import entity.Task;

import java.text.SimpleDateFormat;

public class StudentTasksInteractor implements StudentTasksInputBoundary {
    final StudentTasksDataAccessInterface tasksDAO;
    final StudentTasksOutputBoundary tasksPresenter;

    public StudentTasksInteractor(StudentTasksDataAccessInterface tasksDAO, StudentTasksOutputBoundary tasksPresenter){
        this.tasksDAO = tasksDAO;
        this.tasksPresenter = tasksPresenter;
    }

    @Override
    public void execute(StudentTasksInputData tasksInputData){
        // opens expanded task view of selected task
        // get all the task info needed to be displayed
        // presenter will create the popup w this info
        String taskToDisplay = tasksInputData.getTaskName();

        Task currentTask = tasksInputData.getLoggedIn().getTaskFromName(taskToDisplay);

        assert currentTask != null;
        String name = currentTask.getTaskName();
        SimpleDateFormat deadline = currentTask.getDeadLine();

        if (currentTask instanceof CourseTask){
            Float grade = ((CourseTask) currentTask).getGrade();
            Float weight = ((CourseTask) currentTask).getWeight();
            Course course = ((CourseTask) currentTask).getCourse();
            StudentTasksOutputData outputData = new StudentTasksOutputData(name, deadline, weight, grade, course);
            tasksPresenter.prepareTaskPopup(outputData);
        } else { // StudentTask
            tasksPresenter.prepareEditTaskView((StudentTask) currentTask, tasksInputData.getLoggedIn());
        }

    }
    @Override
    public void executeExit(StudentTasksInputData inputData){
        // x button pressed, go back to user's home screen
        tasksPresenter.prepareExit(inputData.getLoggedIn());
    }
    @Override
    public void executeNewTask(StudentTasksInputData inputData){
        // create new student task
        // open edit task view for this new task to set the values of parameters
        // when creating task asset that task name is unique
        StudentTask newTask = new StudentTask("", "", new SimpleDateFormat());
        tasksPresenter.prepareEditTaskView(newTask, inputData.getLoggedIn());
        // interactor for edit task will create the new task & add to data
    }
}

