package mtesitoo.com.mtesitoo.api;

/**
 * Callback for network requests
 */
public interface Callback<T> {
  void onResult(T result);
  void onError (Exception e);
}
