package use_case.signup_student;


public interface SignupStudentDataAccessInterface {
import entity.Student;

public interface SignupStudentDataAccessInterface {
    boolean existsByEmail(String identifier);

    void save(Student student);
}
