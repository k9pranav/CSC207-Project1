package data_access;

import entity.Admin;
import entity.AdminFactory;

import use_case.signup_admin.SignupAdminDataAccessInterface;

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

//Need to reconsider whether to use Json or CSV
//The only concern with CSV is that a person can be admin of multiple courses
//There is no limit to the courses an admin object administrates
//Using JSON files
// The Structure of JSON files would be:

/*
  { "Admins":
    [
     {"firstname":Pranav,
      "lastname":Kansal,
      "password": somePassword,
      "repeatpassword": somePassword,
      "email": someEmail,
      "courseList": [courseObject1, courseObject2, courseObject2]
      },
     {"firstname":Pranav,
      "lastname":Kansal,
      "password": somePassword,
      "repeatpassword": somePassword,
      "email": someEmail,
      "courseList": [courseObject1, courseObject2, courseObject2]
      }
    ]
  }
*/


public class FileAdminDataAccessObject implements SignupAdminDataAccessInterface {

    private final JSONObject jsonObject;

    private AdminFactory adminFactory;

    public FileAdminDataAccessObject(String jsonPath, AdminFactory adminFactory)
            throws IOException {

        jsonObject = new JSONObject();
    }


    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public void save(Admin admin) {

    }
}