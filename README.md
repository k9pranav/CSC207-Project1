# CSC207-Project1
This is the git hub repository for our CSC207 project

The project domain is Task Management Software for UofT Students and Professors.

The idea is to build a program where professors can upload exam, quiz and assignment dates along with their description and their students will recieve them. This would ensure that students have a centralized system where they can keep track of all their assignents, exams and quizzes all on centralized platform.

The API that we are planning on using is the JIRA API. The link: https://developer.atlassian.com/server/jira/platform/rest-apis/.

According to the documentation, the API allows users to access projects, check their progress, create and distribute projects. The projects can be thought of as exams, quizzes, and tests that are distributed to the students, and the students can get summary of the project. The JIRA API can be used in that regard.

The JIRA API will allow us to contact the Server application, which we can leverage to our advantage, as it can help us store, retrieve and distribute the assignments.
### Screenshot of using the API
Screenshot of using a tool to try out the API (using https://www.postman.com):
![image](https://github.com/k9pranav/CSC207-Project1/assets/132021752/73271187-b7e6-4431-ae6a-3155b9f8e8b0)

### List of Technical Problems that we might face:
  1) Getting the API itself to work
  2) Re-formatting project to Exam, Assignment and Quiz types
  3) Managing the access levels for the two types of users: Students and Admins

### Project Blueprint

#### Domain:
Organizational/Task Management software

#### Software Specifications:
The program would allow Professors/Course Coordinators to distribute assignment deadlines, quiz dates, exam dates and other deliverables to students. This will allow students to get a centralized calendar with all their exam/quiz/assignment deadlines in a centralized calendar. There would be two types of users: administrators (professors, coordinators etc.), who will create/post the deliverables for their course, and students, who will be able to receive the information about their assignment deadlines. This allows students to access this information at one single stop. The student can add their own internal deadlines and tasks in the software.

#### User Stories:
  1) A student Creamy Goat wants to check for upcoming assignments. He would like to see any deadlines around Canadian Thanksgiving so that he can plan his trip and relax accordingly. Instead of looping through the syllabus of every single course, he can just log in to the software to check for his deadlines around the thanksgiving.
  2) Professor Cries is releasing an assignment to his students. He would like to set a due date for this assignment, notify his students about it, and provide details on the assignment’s description and priority. He opens the task management software, logs in as a professor, and creates a new assignment. He enters the information he wants to release to the students, and posts the assignment for the students to see. [Nadja’s story]
  3) Bob has multiple different assignments due during midterm week. The deadlines for each assignment are on different Quercus pages. Bob needs to see the different deadlines all at once and their priority levels to be able to create his study schedule.  [Lasanda’s story]
  4) A student wants to check his course grade up until today. Instead of logging in to the quercus, calculating his grade up until that point (possibly making a mistake in the calculation) he can just log in to the Creamy GOAT’s software, and check his progress up until the course, ie check (i) The total overall percentage he has earned (ii) Out of all assignments whose grades have been released, how much have they earned. [Pranav’s story]
  5) A UofT student has 6 courses and is late for work. He is not sure where to start and how to organize himself to complete all his tasks on time. The task organizer and priority checker will list the deliverables that he has to complete. He will just be able to follow the suggested order of tasks given (and can edit if needed) and be able to stay on top of his work. [Adrien’s story]

#### Proposed Entities for the Domain:
Courses:
  - String courseName
  - String courseCode
  - Integer courseID
  - ArrayList<SuperTask> courseTasks
  - String courseDescription 
  - Administrator courseAdmin
  - ArrayList <Student> studentEnrolled 

Task:
  - String taskName
  - String type
  - DateTime deadline

StudentTask:
- String taskName
- String type
- DateTime deadline

CourseTask:
- String taskName
- String type
- DateTime deadline
- Course taskCourse
- Double taskGrade
- Double taskWeight

Student
  - ArrayList<Task> taskList 
  - ArrayList<Courses> enrolledCourses
  - String password
  - String username
  - ArrayList<Task> studentTasks
  - Dictionary<String, Integer> studentGrades

Methods:
courseTasks (courseID) -> returns a list of all the tasks for that courseID

Administrator:
  - ArrayList<Courses> coursesList
  - String adminName
  - String AdminID
  - String username
  - String password

Use Cases:
- Student signs in
- Admin signs in
- Student logs in
- Student logs out
- Admin logs in
- Admin logs out
- Admin creates task for a course
- Student creates task 
- Student checks for the list of tasks they have
- Student checks for the list of all of their grades
- Student checks for the list of all of their grades in a specific course
- Student checks for their current grade in a course
- Admin assigns grades
  



