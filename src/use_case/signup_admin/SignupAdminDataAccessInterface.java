package use_case.signup_admin;

import entity.Admin;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface SignupAdminDataAccessInterface {
    boolean existsByEmail(String email);

    void save(Admin admin) throws IOException;

    void createCalendar(Admin admin) throws IOException, GeneralSecurityException;
}
