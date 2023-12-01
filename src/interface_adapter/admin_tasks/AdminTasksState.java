package interface_adapter.admin_tasks;

import entity.Admin;

public class AdminTasksState {
    private Admin adminLoggedIn;
    private String currentTaskInfo;

    public AdminTasksState(AdminTasksState copy){
        adminLoggedIn = copy.adminLoggedIn;
    }
    public AdminTasksState(){}
    public void setAdminLoggedIn(Admin admin){this.adminLoggedIn = admin;}
    public Admin getLoggedInUser(){return this.adminLoggedIn;}

    public void setCurrentTaskInfo(String taskInfo) {this.currentTaskInfo = taskInfo;
    }

    public Object getCurrentTaskInfo() {return this.currentTaskInfo;}
}
