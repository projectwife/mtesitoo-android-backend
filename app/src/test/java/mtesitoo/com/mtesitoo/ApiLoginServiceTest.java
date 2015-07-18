package mtesitoo.com.mtesitoo;

import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import mtesitoo.com.mtesitoo.api.ApiLoginService;
import mtesitoo.com.mtesitoo.api.Callback;
import mtesitoo.com.mtesitoo.logic.LoginService;

/**
 * Tests {@link mtesitoo.com.mtesitoo.api.ApiLoginService}
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ApiLoginServiceTest {
  private static final String TAG = ApiLoginServiceTest.class.getSimpleName();
  private LoginService underTest;

  @Before
  public void setUp() {
    ShadowLog.stream = System.out;
    underTest = new ApiLoginService(RuntimeEnvironment.application.getApplicationContext());
  }

  @Test
  public void authToken() throws InterruptedException {
    Log.d(TAG, "Starting test...");
    final CountDownLatch latch = new CountDownLatch(1);
    final StringBuilder token = new StringBuilder();
    underTest.getAuthToken(new Callback<String>() {
      @Override
      public void onResult(String result) {
        token.append(result);
        Log.d(TAG, "Got auth token: result");
        latch.countDown();
      }

      @Override
      public void onError(Exception e) {
        token.append(e);
        Log.e(TAG, "Error getting token", e);
        latch.countDown();
      }
    });
    latch.await(10, TimeUnit.SECONDS);
    Log.d(TAG, "Test ended: " + token);
  }
}
