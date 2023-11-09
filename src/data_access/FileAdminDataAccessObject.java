package data_access;

import entity.Admin;
import entity.AdminFactory;

import use_case.signup_admin.SignUpAdminDataAccessInterface;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//Need to reconsider whether to use Json or CSV
//The only concern with CSV is that a person can be admin of multiple courses
//There is no limit to the courses an admin object administrates
public class FileAdminDataAccessObject implements SignUpAdminDataAccessInterface {

    private final JSONObject jsonObject;

    private AdminFactory adminFactory;

    public FileAdminDataAccessObject(String jsonPath, AdminFactory adminFactory)
            throws IOException {

        jsonObject = new JSONObject()
    }


}