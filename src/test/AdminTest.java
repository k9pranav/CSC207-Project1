import static org.junit.jupiter.api.Assertions.*;

import entity.Admin;

class AdminTest {

    private Admin admin;

    Admin testAdmin = Admin("Pranav", "Kansal", "MyPassword",
            "MyPassword", "pranav.kansal@mail.utoronto.ca");


    @org.junit.jupiter.api.Test
    void getFirstName() {
        assertEquals("Pranav", testAdmin.getFirstName());
    }

    @org.junit.jupiter.api.Test
    void getLastName() {
        assertEquals("Pranav", testAdmin.getLastName());
    }

    @org.junit.jupiter.api.Test
    void getPassword() {
        assertEquals("MyPassword", testAdmin.getPassword());
    }

    @org.junit.jupiter.api.Test
    void getRepeatPassword() {
        assertEquals("MyPassword", testAdmin.getRepearPassword());
    }

    @org.junit.jupiter.api.Test
    void getEmail() {
        assertEquals("pranav.kansal@mail.utoronto.ca", testAdmin.getEmail());
    }


    @org.junit.jupiter.api.Test
    void getCourses() {
        assertEquals([], testAdmin.getCourses());
    }


    @org.junit.jupiter.api.Test
    void getCalendarId() {
        assertEquals("", testAdmin.getCalendarId())
    }




}