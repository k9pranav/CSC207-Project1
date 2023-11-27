package use_case.signup_student;


import entity.Student;

import java.io.IOException;

public interface SignupStudentDataAccessInterface {
    boolean existsByEmail(String identifier);

    void save(Student student) throws IOException;
}
