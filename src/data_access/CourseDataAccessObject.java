package data_access;

import entity.Admin;
import entity.Course;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CourseDataAccessObject {
    private final Map<String, Course> courses = new HashMap<>();
    private final String pathToFile;
    private final String courseNameKey = "name";
    private final String courseCodeKey = "code";
    private final String courseYearKey = "year";
    private final String courseDescriptionKey = "description";
    private final String courseSessionKey = "session";
    private final String courseAdminKey = "admin";
    public CourseDataAccessObject(String pathToFile) {
        this.pathToFile = pathToFile;
        File jsonFile = new File(pathToFile);
        try {
            //TODO add tasks
            JSONObject jsonObject = new JSONObject(FileUtils.readFileToString(jsonFile, "UTF-8"));
            for(String key: jsonObject.keySet()) {
                JSONObject jsonObj = (JSONObject) jsonObject.get(key);
                // TODO manage admin, it should be just te key here
                Admin admin = new Admin((String)jsonObj.get(this.courseAdminKey),"","", "","");
                Course course  = new Course(
                        (String) jsonObj.get(this.courseNameKey),
                        (String) jsonObj.get(this.courseCodeKey),
                        (Integer) jsonObj.get(this.courseYearKey),
                        (String) jsonObj.get(this.courseSessionKey),
                        (String) jsonObj.get(this.courseDescriptionKey),
                        admin);
                courses.put(key, course);
            }
        }
        catch(Exception e) {
            // TODO handle better
            System.out.println("Error - Course file "+ pathToFile+ " must exist");
        }
    }
    public Course get(String code) {
        return courses.get(code);
    }
}
