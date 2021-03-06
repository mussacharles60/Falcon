package com.jraska.falcon.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.jraska.falcon.FalconSpoonRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.support.test.rule.GrantPermissionRule.grant;
import static com.jraska.falcon.sample.asserts.BitmapFileAssert.assertThatFile;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Shows usage of {@link FalconSpoonRule} compat screenshots
 */
@RunWith(AndroidJUnit4.class)
public class FalconSpoonTest {

  //region Fields

  @Rule
  public ActivityTestRule<SampleActivity> _activityRule = new ActivityTestRule<>(
      SampleActivity.class);

  @Rule
  public final FalconSpoonRule _falconSpoonRule = new FalconSpoonRule();

  @Rule
  public final GrantPermissionRule permissionRule = grant(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE);

  private File _screenshotFile;

  //endregion

  //region Setup Methods

  @After
  public void after() throws Exception {
    if (_screenshotFile != null) {
      assertThat(_screenshotFile.delete()).isTrue();
    }
  }

  //endregion

  //region Test methods

  @Test
  public void takesScreenshotToFile() throws Exception {
    String tag = "ExampleScreenshot";
    _screenshotFile = _falconSpoonRule.screenshot(_activityRule.getActivity(), tag);

    assertThat(_screenshotFile.length()).isGreaterThan(0L);
    assertThatFile(_screenshotFile).isBitmap();
  }

  //endregion
}
