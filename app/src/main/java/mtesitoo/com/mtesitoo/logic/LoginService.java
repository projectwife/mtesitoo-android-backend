package mtesitoo.com.mtesitoo.logic;

import mtesitoo.com.mtesitoo.api.Callback;

/**
 * Service for getting an OAuth token for Accessing the Api.
 * 
 * @author danieldanciu
 */
public interface LoginService {
  void getAuthToken(final Callback<String> callback);
}
