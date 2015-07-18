package mtesitoo.com.mtesitoo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import mtesitoo.com.mtesitoo.api.ApiLoginService;
import mtesitoo.com.mtesitoo.api.Callback;


public class MainActivity extends ActionBarActivity {

  private static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ApiLoginService apiLoginService = new ApiLoginService(this);
    apiLoginService.getAuthToken(new Callback<String>() {
      @Override
      public void onResult(String result) {
        Log.d(TAG, "Got auth token: " + result);
      }

      @Override
      public void onError(Exception e) {
        Log.e(TAG, "Error getting token", e);
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
