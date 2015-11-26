package project.com.snapask.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import javax.security.auth.callback.Callback;

import project.com.snapask.model.UserProfile;

/**
 * Created by Vison on 2015/11/26.
 */
public class ApiService {

    private Context mContext;

    public ApiService(Context context) {
        mContext = context;
    }

    public void getUserProfile(final GetProfileCallback callback) {
        String url = "https://api.myjson.com/bins/4zujh";
        RequestQueue queue = Volley.newRequestQueue(mContext);
        final JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    if (status.equals("success")) {
                        Gson gson = new Gson();
                        String json = response.getString("data");
                        Type userProfileClass = new TypeToken<UserProfile>(){}.getType();
                        UserProfile userProfile = gson.fromJson(json, userProfileClass);
                        callback.onSuccess(userProfile);
                    } else {
                        callback.onError(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });

        queue.add(request);
    }

}
