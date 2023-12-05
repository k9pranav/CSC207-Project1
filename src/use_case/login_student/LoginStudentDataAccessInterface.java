package use_case.login_student;

import entity.Student;

import java.io.IOException;

public interface LoginStudentDataAccessInterface {

    void save(Student student) throws IOException;

    Student get(String email);

    boolean existsByEmail(String identifier);
}
