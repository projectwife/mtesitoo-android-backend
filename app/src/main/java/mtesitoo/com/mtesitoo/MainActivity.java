package mtesitoo.com.mtesitoo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import mtesitoo.com.mtesitoo.api.ApiLoginService;
import mtesitoo.com.mtesitoo.api.ApiProductService;
import mtesitoo.com.mtesitoo.api.Callback;
import mtesitoo.com.mtesitoo.model.Product;


public class MainActivity extends ActionBarActivity {

  private static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final TextView tv = (TextView)  MainActivity.this.findViewById(R.id.textView);
    ApiLoginService apiLoginService = new ApiLoginService(this);
    apiLoginService.getAuthToken(new Callback<String>() {
      @Override
      public void onResult(String result) {
        Log.d(TAG, "Got auth token: " + result);
        tv.setText("Got auth token: " + result);
      }

      @Override
      public void onError(Exception e) {
        Log.e(TAG, "Error getting token", e);
      }
    });
    ApiProductService apiProductService = new ApiProductService(this);
    apiProductService.getSpecials(new Callback<List<Product>>() {
      @Override
      public void onResult(List<Product> result) {
        Log.d(TAG, "Products are: " + result);
        tv.setText("Products are: " + result);
      }

      @Override
      public void onError(Exception e) {
        Log.d(TAG, "Error retrieving products: " + e);
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

    // noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
