// this is attempting to get the information of an issue from the JIRA API.

package api;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class API_Example_Call {
    private static final String API_URL = "https://jira.atlassian.com/rest/api/latest/issue/";
    private static final String API_TOKEN = System.getenv("API_TOKEN");
    public static String getApiToken() {
            return API_TOKEN;
        }

    public String getIssue(String issue) throws JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("issue", issue);
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("https://jira.atlassian.com/rest/api/latest/issue/JRA-9")
                .method("POST", body)
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                return null;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


}


