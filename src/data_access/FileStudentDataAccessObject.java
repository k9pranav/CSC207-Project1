package data_access;

import app.CourseFactory;
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
import entity.Course;
import entity.Student;
import entity.StudentFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.login_student.LoginStudentDataAccessInterface;
import use_case.signup_student.SignupStudentDataAccessInterface;
import org.apache.commons.io.FileUtils;


import java.io.*;
import java.security.GeneralSecurityException;
import java.util.*;

public class FileStudentDataAccessObject implements SignupStudentDataAccessInterface, LoginStudentDataAccessInterface {
    private final JSONObject jsonObject;
    private StudentFactory studentFactory;
    private final Map<String, Student> accounts = new HashMap<>();
    private static final String APPLICATION_NAME = "Creamy GOATS";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static final String studentsKey = "students";
    private static final String firstNameKey = "firstname";
    private static final String lastNameKey = "lastname";
    private static final String passwordKey = "password";
    private static final String emailKey = "email";
    private static final String calendarIDKey = "calendarID";
    private static final String courseListKey = "courseList";


    private static final String TOKENS_DIRECTORY_PATH = "tokens2";
    // not sure if we should make a separate folder for these tokens too..

    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "main/resources/credentials.json";
    private final String pathToFile;

    public FileStudentDataAccessObject(String pathToFile, StudentFactory studentFactory) throws IOException{
        this.studentFactory = studentFactory;
        this.pathToFile = pathToFile;
        File jsonFile = new File(pathToFile);
        String jsonString = "{}";
        if (jsonFile.exists()) {
            jsonString = FileUtils.readFileToString(jsonFile, "UTF-8");
        }
        this.jsonObject = new JSONObject(jsonString);

        if (!jsonObject.has(this.studentsKey)){
            jsonObject.put(this.studentsKey, new JSONArray());
            save();
        } else {
            try {
                JSONArray students = jsonObject.getJSONArray(this.studentsKey);
                for (int i = 0; i < students.length(); i++) {
                    JSONObject j = (JSONObject) students.get(i);
                    String firstName = (String) j.get(this.firstNameKey);
                    String lastName = (String) j.get(this.lastNameKey);
                    String password = (String) j.get(this.passwordKey);
                    String email = (String) j.get(this.emailKey);
                    // TODO remove repeat password from the factory, no need to store it
                    Student student = (Student) studentFactory.create(firstName, lastName, password, password, email);
                    // TODO add CalendarIDKey
                    JSONArray courses = j.getJSONArray(this.courseListKey);
                    for (int k = 0; k < courses.length(); k++) {
                        String course_code = (String) courses.get(k);
                        student.setCourse(CourseFactory.load(course_code));
                    }

                    accounts.put(student.getEmail(), student);
                } // file has emails as the key and a list of JSON files for each admin
        }catch (JSONException e){
                throw new RuntimeException(e);
            }
    }
}
private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException{
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

public void createCalendar(Student student) throws IOException, GeneralSecurityException{
    // use case interactor calls this method when a person signs up to add calendar to new user
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

    Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
            .setApplicationName("applicationName").build();


    // Create a new calendar
    com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
    calendar.setSummary(student.getEmail());
    calendar.setTimeZone("America/Toronto");

    // Insert the new calendar
    com.google.api.services.calendar.model.Calendar createdCalendar = service.calendars().insert(calendar).execute();

    student.setCalendarID(createdCalendar.getId()); // sets the admin's calendar id
}

    @Override
    public boolean existsByEmail(String email) {
        return accounts.containsKey(email);
    }


    @Override
    public void save(Student student) throws IOException {
        accounts.put(student.getEmail(), student);
        this.save();
    }

    @Override
    public Student get(String email) {
        return accounts.get(email);
    }

    private void save() throws IOException{
        JSONArray studentArray = jsonObject.getJSONArray(this.studentsKey);
            for (Student student : accounts.values()){

            JSONObject jsonObj = new JSONObject();
            jsonObj.put(this.firstNameKey, student.getFirstName());
            jsonObj.put(this.lastNameKey, student.getLastName());
            jsonObj.put(this.passwordKey, student.getPassword());
            jsonObj.put(this.emailKey, student.getEmail());
            jsonObj.put(this.calendarIDKey, student.getCalendarId());
            ArrayList<Course> a = student.getCourses();
            JSONArray j = new JSONArray(a);
            jsonObj.put(this.courseListKey, j );
            studentArray.put(jsonObj);
        }
        jsonObject.put(this.studentsKey, studentArray);

        FileWriter file = new FileWriter(pathToFile, false);
        file.write(jsonObject.toString());
        file.close();
    }
}