package use_case.signup_admin;

public interface SignUpAdminDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User admin);
}