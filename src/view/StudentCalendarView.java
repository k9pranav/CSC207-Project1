package view;

import entity.*;
import interface_adapter.go_back_student.GoBackStudentController;
import interface_adapter.go_back_student.GoBackStudentState;
import interface_adapter.go_back_student.GoBackStudentViewModel;
import interface_adapter.student_tasks.StudentTasksController;
import interface_adapter.student_tasks.StudentTasksState;
import interface_adapter.student_tasks.StudentTasksViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

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
        //updateEventYearMap(allTasks);


        JPanel contentPane = new JPanel(new BorderLayout());
        this.add(contentPane);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prevButton = new JButton("<< Prev");
        prevButton.addActionListener(e -> navigateMonth(-1));
        JButton nextButton = new JButton("Next >>");
        nextButton.addActionListener(e -> navigateMonth(1));
        monthLabel = new JLabel("", SwingConstants.CENTER);
        headerPanel.add(prevButton);
        headerPanel.add(monthLabel);
        headerPanel.add(nextButton);
        contentPane.add(headerPanel, BorderLayout.NORTH);

        daysPanel = new JPanel(new GridLayout(0, 7, 5, 5));
        contentPane.add(daysPanel, BorderLayout.CENTER);


        this.goBackBtn = new JButton();
        this.footer = new JPanel();
        footer.setPreferredSize(new Dimension(4000, 100));
        this.email = goBackStudentViewModel.getState().getEmail();
        this.accountType = goBackStudentViewModel.getState().getEmail();

        footer.add(goBackBtn);
        this.add(footer, BorderLayout.SOUTH);

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

        currentDate = LocalDate.now();
        displayMonth(currentDate);
    }

    private void displayMonth(LocalDate date) {
        updateEventYearMap(allTasks);

        monthLabel.setText(date.getMonth().toString() + " " + date.getYear());

        daysPanel.removeAll();
        for (DayOfWeek day : DayOfWeek.values()) {
            JLabel label = new JLabel(day.toString().substring(0, 3), SwingConstants.CENTER);
            daysPanel.add(label);
        }

        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        int emptySlots = firstDayOfMonth.getDayOfWeek().getValue() % 7;
        for (int i = 0; i < emptySlots; i++) {
            daysPanel.add(new JLabel());
        }

        LocalDate tempDate = firstDayOfMonth;
        while (tempDate.getMonth().equals(date.getMonth())) {
            JPanel dayPanel = new JPanel();
            dayPanel.setBackground(new Color(200,0,0,255));
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));

            JLabel dayLabel = new JLabel(String.valueOf(tempDate.getDayOfMonth()), SwingConstants.CENTER);
            dayLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
            dayPanel.add(dayLabel);

            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> list = new JList<>(listModel);
            addTaskToList(listModel, date);


            list.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        tasksController.execute(list.getSelectedValue(), tasksViewModel.getState().getLoggedInUser());

                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane();
            dayPanel.add(scrollPane);
            scrollPane.add(list);

            daysPanel.add(dayPanel);
            tempDate = tempDate.plusDays(1);
        }

        revalidate();
        repaint();
    }

    private void addTaskToList(DefaultListModel listModel, LocalDate date) {
        if(yearHashMap.containsKey(date.getYear())){
            Year year = yearHashMap.get(date.getYear());
            if(year.hasMonth(date.getMonthValue() - 1)){
                Month month = year.getMonth(date.getMonthValue() - 1);
                for(Week week : month){
                    for (Day day : week){
                        if (day.getDayOfTheMonth() == date.getDayOfMonth()){
                            ArrayList<String> sortedList = getSortedTaskTimes(day);
                            for (String time: sortedList) {
                                listModel.addElement(day.getTask(time).getTaskName() + "Time: " + time);
                            }
                        }
                    }
                }
            }
        }

    }

    private static ArrayList<String> getSortedTaskTimes(Day day) {
        Set<String> taskDeadlines = day.getMapOfTasks().keySet();
        ArrayList<String> sortedList = new ArrayList<>(taskDeadlines);
        sortedList.sort(new Comparator<String>() {
            @Override
            public int compare(String time1, String time2) {
                String[] time1Parts = time1.split(":");
                String[] time2Parts = time2.split(":");

                for (int i = 0; i < 3; i++) {
                    int timePart1 = Integer.parseInt(time1Parts[i]);
                    int timePart2 = Integer.parseInt(time2Parts[i]);

                    if (timePart1 != timePart2) {
                        return Integer.compare(timePart1, timePart2);
                    }
                }

                return 0;
            }
        });
        return sortedList;
    }

    private void navigateMonth(int offset) {
        currentDate = currentDate.plusMonths(offset);
        displayMonth(currentDate);
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
                            deadlineDayObj.addTasks(allTasks.get(i).getDeadLine(), allTasks.get(i));

                            deadlineWeekObj.addDays(deadlineDayObj);
                        } else {
                            Day deadlineDayObj = DayFactory.create(deadlineMonthObj, deadlineDay, deadlineDayOfWeek );
                            deadlineDayObj.addTasks(allTasks.get(i).getDeadLine(), allTasks.get(i));

                            deadlineWeekObj.addDays(deadlineDayObj);
                        }
                        deadlineMonthObj.addWeeks(deadlineWeekObj);

                    } else {
                        Week deadlineWeekObj = WeekFactory.create(deadlineMonthObj, deadlineWeek);
                        Day deadlineDayObj = DayFactory.create(deadlineMonthObj, deadlineDay, deadlineDayOfWeek );
                        deadlineDayObj.addTasks(allTasks.get(i).getDeadLine(), allTasks.get(i));
                        deadlineWeekObj.addDays(deadlineDayObj);

                        deadlineMonthObj.addWeeks(deadlineWeekObj);
                    }
                    deadlineYearObj.addMonth(deadlineMonthObj);
                } else {
                    Month deadlineMonthObj = MonthFactory.create(deadlineMonth, deadlineYearObj.getYear());
                    Week deadlineWeekObj = WeekFactory.create(deadlineMonthObj, deadlineWeek);
                    Day deadlineDayObj = DayFactory.create(deadlineMonthObj, deadlineDay, deadlineDayOfWeek );
                    deadlineDayObj.addTasks(allTasks.get(i).getDeadLine(), allTasks.get(i));
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
                deadlineDayObj.addTasks(allTasks.get(i).getDeadLine(), allTasks.get(i));
                deadlineWeekObj.addDays(deadlineDayObj);
                deadlineMonthObj.addWeeks(deadlineWeekObj);
                deadlineYearObj.addMonth(deadlineMonthObj);
                yearHashMap.put(deadlineYear, deadlineYearObj);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click" + e.getActionCommand());
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propName = evt.getPropertyName();
        if (propName.equals("popup")) {
            StudentTasksState currState = (StudentTasksState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, currState.getCurrentTaskInfo());
        }
    }
}
