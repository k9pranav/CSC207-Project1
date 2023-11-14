package use_case.login_student;

import entity.Student;

public interface LoginStudentDataAccessInterface {
    boolean existByName(String identifier);

    void save(Student student);

    Student get(String email);

}
