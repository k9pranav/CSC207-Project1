package view;

import entity.*;
import interface_adapter.go_back_student.GoBackStudentController;
import interface_adapter.go_back_student.GoBackStudentState;
import interface_adapter.go_back_student.GoBackStudentViewModel;
import interface_adapter.student_tasks.StudentTasksController;
import interface_adapter.student_tasks.StudentTasksViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class StudentCalendarView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "student calendar page";

    private JLabel monthLabel;

    private JPanel daysPanel;
    private LocalDate currentDate;
    private final JPanel footer;
    private final JButton goBackBtn;

    private final String email;

    private final String accountType;

    private final StudentTasksController tasksController;

    private final StudentTasksViewModel tasksViewModel;

    private final ArrayList<Task> allTasks;

    final ArrayList<JList<String>> tasks = new ArrayList<>();

    final HashMap<Integer, Year> yearHashMap = new HashMap<>();


    public StudentCalendarView(GoBackStudentController goBackStudentController, GoBackStudentViewModel goBackStudentViewModel, StudentTasksViewModel studentTasksViewModel, StudentTasksController studentTasksController){
        setSize(4000, 3000);


        this.tasksViewModel= studentTasksViewModel;
        this.tasksController = studentTasksController;

        Student student = tasksViewModel.getState().getLoggedInUser();

        this.allTasks = student.getTasks();
        updateEventYearMap(allTasks);






        this.goBackBtn = new JButton("Go Back");
        this.footer = new JPanel();
        this.email = goBackStudentViewModel.getState().getEmail();
        this.accountType = goBackStudentViewModel.getState().getEmail();

        footer.add(goBackBtn);

        goBackBtn.addActionListener(
                new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e){
                        if (e.getSource().equals(goBackBtn)){
                            GoBackStudentState currentGoBackStudentState = goBackStudentViewModel.getState();
                            goBackStudentController.execute(email, accountType);
                        }
                    }
                }
        );
    }


    private void updateEventYearMap( ArrayList<Task> allTasks){
        for (int i = 0; i < allTasks.size(); i++) {
            Calendar deadlineCalendar = allTasks.get(i).getDeadLine().getCalendar();

            int deadlineYear =  deadlineCalendar.get(Calendar.YEAR); // ex. 2023
            int deadlineMonth = deadlineCalendar.get(Calendar.MONTH); // 0 to 11
            int deadlineWeek = deadlineCalendar.get(Calendar.WEEK_OF_MONTH); // 0 to total
            int deadlineDay = deadlineCalendar.get(Calendar.DAY_OF_MONTH); // 1 to max
            int deadlineDayOfWeek = deadlineCalendar.get(Calendar.DAY_OF_WEEK); // 1 to 7

            if (yearHashMap.containsKey(deadlineYear)){
                Year deadlineYearObj = yearHashMap.get(deadlineYear);

                if (deadlineYearObj.hasMonth(deadlineMonth)){
                    Month deadlineMonthObj = deadlineYearObj.getMonth(deadlineMonth);

                    if (deadlineMonthObj.hasWeek(deadlineWeek)){
                        Week deadlineWeekObj = deadlineMonthObj.getWeek(deadlineWeek);

                        if (deadlineWeekObj.hasDay(deadlineDayOfWeek)){
                            Day deadlineDayObj = deadlineWeekObj.getDay(deadlineDayOfWeek);
                            deadlineDayObj.addTasks(allTasks.get(i).getTaskName(), allTasks.get(i));

                            deadlineWeekObj.addDays(deadlineDayObj);
                        } else {
                            Day deadlineDayObj = DayFactory.create(deadlineMonthObj, deadlineDay, deadlineDayOfWeek );
                            deadlineDayObj.addTasks(allTasks.get(i).getTaskName(), allTasks.get(i));

                            deadlineWeekObj.addDays(deadlineDayObj);
                        }
                        deadlineMonthObj.addWeeks(deadlineWeekObj);

                    } else {
                        Week deadlineWeekObj = WeekFactory.create(deadlineMonthObj, deadlineWeek);
                        Day deadlineDayObj = DayFactory.create(deadlineMonthObj, deadlineDay, deadlineDayOfWeek );
                        deadlineDayObj.addTasks(allTasks.get(i).getTaskName(), allTasks.get(i));
                        deadlineWeekObj.addDays(deadlineDayObj);

                        deadlineMonthObj.addWeeks(deadlineWeekObj);
                    }
                    deadlineYearObj.addMonth(deadlineMonthObj);
                } else {
                    Month deadlineMonthObj = MonthFactory.create(deadlineMonth, deadlineYearObj.getYear());
                    Week deadlineWeekObj = WeekFactory.create(deadlineMonthObj, deadlineWeek);
                    Day deadlineDayObj = DayFactory.create(deadlineMonthObj, deadlineDay, deadlineDayOfWeek );
                    deadlineDayObj.addTasks(allTasks.get(i).getTaskName(), allTasks.get(i));
                    deadlineWeekObj.addDays(deadlineDayObj);
                    deadlineMonthObj.addWeeks(deadlineWeekObj);

                    deadlineYearObj.addMonth(deadlineMonthObj);

                }
                yearHashMap.put(deadlineYear, deadlineYearObj);
            } else {
                Year deadlineYearObj = YearFactory.create(deadlineYear);
                Month deadlineMonthObj = MonthFactory.create(deadlineMonth, deadlineYearObj.getYear());
                Week deadlineWeekObj = WeekFactory.create(deadlineMonthObj, deadlineWeek);
                Day deadlineDayObj = DayFactory.create(deadlineMonthObj, deadlineDay, deadlineDayOfWeek );
                deadlineDayObj.addTasks(allTasks.get(i).getTaskName(), allTasks.get(i));
                deadlineWeekObj.addDays(deadlineDayObj);
                deadlineMonthObj.addWeeks(deadlineWeekObj);
                deadlineYearObj.addMonth(deadlineMonthObj);
                yearHashMap.put(deadlineYear, deadlineYearObj);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
