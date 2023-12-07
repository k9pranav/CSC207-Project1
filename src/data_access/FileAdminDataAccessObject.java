package data_access;

import entity.Admin;
import entity.AdminFactory;

import entity.Student;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import use_case.login_admin.LoginAdminDataAccessInterface;
import use_case.login_student.LoginStudentDataAccessInterface;
import use_case.signup_admin.SignupAdminDataAccessInterface;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

import java.io.*;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;


public class FileAdminDataAccessObject implements SignupAdminDataAccessInterface, LoginAdminDataAccessInterface {

    private final JSONObject jsonObject;

    private AdminFactory adminFactory;
  
    private final Map<String, Admin> accounts = new HashMap<>();
    //list of all the admin accounts mapped email -> entity
    private static final String APPLICATION_NAME = "Creamy GOATS";

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static final String TOKENS_DIRECTORY_PATH = "tokens2";

    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "main/resources/credentials.json";

    private final String pathToFile;

    private static final String adminsKey = "admins";
    private static final String firstNameKey = "firstname";
    private static final String lastNameKey = "lastname";
    private static final String passwordKey = "password";
    private static final String emailKey = "email";
    private static final String calendarIDKey = "calendarID";
    private static final String courseListKey = "courseList";
    public FileAdminDataAccessObject(String pathToFile, AdminFactory adminFactory) throws IOException {

        this.adminFactory = adminFactory;
        this.pathToFile = pathToFile;
        File jsonFile = new File(pathToFile);
        String jsonString = "{}";
        if (jsonFile.exists()) {
            jsonString = FileUtils.readFileToString(jsonFile, "UTF-8");
        }
        this.jsonObject = new JSONObject(jsonString);
        // the JSON file with all the admins

        if (!jsonObject.has(this.adminsKey)){
            jsonObject.put(this.adminsKey, new JSONArray());
            save();
        }else {
            try {
                JSONArray admins = jsonObject.getJSONArray(this.adminsKey);
                for (int i = 0; i < admins.length(); i++) {
                    JSONObject j = (JSONObject) admins.get(i);
                    String firstName = (String) j.get(this.firstNameKey);
                    String lastName = (String) j.get(this.lastNameKey);
                    String password = (String) j.get(this.passwordKey);
                    String email = (String) j.get(this.emailKey);
                    // TODO remove repeat password from the factory, no need to store it
                    Admin admin = adminFactory.create(firstName, lastName, password, password, email);
                    accounts.put(admin.getEmail(), admin);
                }
                // the file has emails as the key and then a list of JSON files for each admin
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
        private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = FileAdminDataAccessObject.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }
    public void createCalendar(Admin admin) throws IOException, GeneralSecurityException {

        // use case interactor calls this method when a person signs up to add calendar to new user
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName("applicationName").build();


        // Create a new calendar
        com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
        calendar.setSummary(admin.getEmail());
        calendar.setTimeZone("America/Toronto");

        // Insert the new calendar
        com.google.api.services.calendar.model.Calendar createdCalendar = service.calendars().insert(calendar).execute();

        admin.setCalendarID(createdCalendar.getId()); // sets the admin's calendar id
    }
    @Override
    public boolean existsByEmail(String email) {
        return accounts.containsKey(email);
    }

    @Override
    public boolean existByName(String email) {
        return accounts.containsKey(email);
    }

    @Override

    public void save(Admin admin) throws IOException {

        accounts.put(admin.getEmail(), admin);
        this.save();
    }

    @Override
    public Admin get(String email) {
        return accounts.get(email);
    }

    private void save() throws IOException{
        JSONArray adminArray = jsonObject.getJSONArray(this.adminsKey);
        for (Admin admin : accounts.values()){

            JSONObject jsonObj = new JSONObject();
            jsonObj.put(this.firstNameKey, admin.getFirstName());
            jsonObj.put(this.lastNameKey, admin.getLastName());
            jsonObj.put(this.passwordKey, admin.getPassword());
            jsonObj.put(this.emailKey, admin.getEmail());
            jsonObj.put(this.calendarIDKey, admin.getCalendarId());
            jsonObj.put(this.courseListKey, admin.getCourses());
            adminArray.put(jsonObj);
        }
        jsonObject.put(this.adminsKey, adminArray);
        FileWriter file = new FileWriter(pathToFile, false);
        file.write(jsonObject.toString());
        file.close();
    }
}