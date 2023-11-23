package use_case.student_courses;

import entity.Student;

public interface StudentCoursesOutputBoundary {
    void prepareExit(Student student);
    void prepareAverage(Float avg, Student student);
    void prepareCourseTasksView(StudentCoursesOutputData outputData);
}

