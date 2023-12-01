package interface_adapter.admin_courses;

import entity.Admin;

public class AdminCoursesState {
    private Admin adminLoggedIn;

    public AdminCoursesState(AdminCoursesState copy){
        adminLoggedIn = copy.adminLoggedIn;
    }

    public AdminCoursesState(){}
    public void setAdminLoggedIn(Admin admin){this.adminLoggedIn = admin;}
    public Admin getLoggedInUser(){return this.adminLoggedIn;}

}
