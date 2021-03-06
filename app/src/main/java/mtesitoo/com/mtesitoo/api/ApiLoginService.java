package mtesitoo.com.mtesitoo.api;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import mtesitoo.com.mtesitoo.C;
import mtesitoo.com.mtesitoo.logic.LoginService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Api-based implementation of {@link LoginService}
 */
public class ApiLoginService implements LoginService {
  private static final String CLIENT_ID = "mtesitooclientid";
  private static final String CLIENT_SECRET = "mtesitooclientsecret";
  private static final String TAG = ApiLoginService.class.getSimpleName();
  private final RequestQueue queue;
  /**
   * Caching oauth tokens for the lifetime of the app, since we set OpenCart to never expire tokens.
   */
  private String oauthToken;

  public ApiLoginService(Context context) {
    queue = Volley.newRequestQueue(context);
  }

  public void getAuthToken(final Callback<String> callback) {
    if (oauthToken != null) {
      callback.onResult(oauthToken);
      return;
    }

    String url = C.api.server + C.api.oauth_path;

    // Request a string response from the provided URL.
    StringRequest stringRequest =
        new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            Log.d(TAG, "Response is: " + response);
            JSONObject jsonResponse = null;
            try {
              jsonResponse = new JSONObject(response);
              String oauthToken = jsonResponse.getString("access_token");
              ApiLoginService.this.oauthToken = oauthToken;
              callback.onResult(oauthToken);
            } catch (JSONException e) {
              callback.onError(e);
            }
          }
        }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            Log.d(TAG, "Error getting oauth token: " + error);
            callback.onError(new Exception(error));
          }
        }) {
          @Override
          public HashMap<String, String> getHeaders() {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("Authorization", getAuthorization());
            params.put("Accept", "application/json; charset=utf-8");
            Log.d(TAG, "Headers are: " + params);
            return params;
          }

          @Override
          protected Map<String, String> getParams() throws AuthFailureError {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("grant_type", "client_credentials");
            return params;
          }

        };
    // Add the request to the RequestQueue.
    queue.add(stringRequest);
  }

  private String getAuthorization() {
    return "Basic "
        + Base64.encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes(), Base64.NO_WRAP);
  }
}
