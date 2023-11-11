package use_case.login_admin;

import entity.Admin;

public interface LoginAdminDataAccessInterface {

    boolean existByName(String identifier);

    void save(Admin admin);

    Admin get(String email);
}
