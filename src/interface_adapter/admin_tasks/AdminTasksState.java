package interface_adapter.admin_tasks;

import entity.Admin;

public class AdminTasksState {
    private Admin adminLoggedIn;

    public AdminTasksState(AdminTasksState copy){
        adminLoggedIn = copy.adminLoggedIn;
    }
    public AdminTasksState(){}
    public void setAdminLoggedIn(Admin admin){this.adminLoggedIn = admin;}
    public Admin getLoggedInUser(){return this.adminLoggedIn;}

}
