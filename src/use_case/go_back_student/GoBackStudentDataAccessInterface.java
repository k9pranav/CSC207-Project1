package use_case.go_back_student;

import entity.Person;

public interface GoBackStudentDataAccessInterface {

    Person get(String email);

}
