package data_access;

import entity.Admin;
import entity.AdminFactory;

import use_case.signup_admin.SignupAdminDataAccessInterface;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;


public class FileAdminDataAccessObject implements SignupAdminDataAccessInterface {

    private final JSONObject jsonObject;

    private AdminFactory adminFactory;

    private final Map<String,Integer> headers = new LinkedHashMap<>();
    // headers stores what index each header is in, in the JSON file
    private final Map<String, Admin> accounts = new HashMap<>();
    //list of all the admin accounts mapped email -> entity
    private static final String APPLICATION_NAME = "Creamy GOATS";

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static final String TOKENS_DIRECTORY_PATH = "tokens2";

    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "main/resources/credentials.json";

    public FileAdminDataAccessObject(AdminFactory adminFactory){
        this.adminFactory = adminFactory;
        jsonObject = new JSONObject();
        // headers might not be necessary, but shows the order of the columns in JSON file
        headers.put("firstname", 0);
        headers.put("lastname", 1);
        headers.put("password", 2);
        headers.put("email", 3);
        headers.put("calendarId", 4);
        headers.put("courseList", 5);


        // read the file, create user for each user in file and add user to accounts
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
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }
    public void createCalendar(Admin admin) {
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
    public void save(Admin admin) {
        accounts.put(admin.getEmail(), admin);
        this.save();
    }

    private void save() throws IOException{
        for (Admin admin : accounts.values()){
            jsonObject.put("firstname", admin.getFirstName());
            jsonObject.put("lastname", admin.getLastName());
            jsonObject.put("password", admin.getPassword());
            jsonObject.put("email", admin.getEmail());
            jsonObject.put("calendarId", admin.getCalendarId());
            jsonObject.put("courseList", admin.getCourses());
        }
        fileWriter file = new FileWriter("path-to-file", false);
        file.write(jsonObject.toJSONString());
        file.close();
    }
}