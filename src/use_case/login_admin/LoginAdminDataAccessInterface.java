package use_case.login_admin;

import entity.Admin;

import java.io.IOException;

public interface LoginAdminDataAccessInterface {

    boolean existByEmail(String identifier);

    void save(Admin admin) throws IOException;

    Admin get(String email);
}
