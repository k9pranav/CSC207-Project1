package use_case.signup_student;

import entity.Student;

public interface SignupStudentDataAccessInterface {
    boolean existsById(String identifier);

    void save(Student student);
}
