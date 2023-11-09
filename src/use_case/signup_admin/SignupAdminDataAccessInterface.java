package use_case.signup_admin;

import entity.Admin;

public interface SignupAdminDataAccessInterface {
    boolean existsByEmail(String email);

    void save(Admin admin);
}
