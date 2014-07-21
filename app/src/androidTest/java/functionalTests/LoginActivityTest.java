package functionalTests;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jayway.android.robotium.solo.Solo;
import com.squareup.spoon.Spoon;
import com.testSuite.stevenramzel.testsuite.LoginActivity;
import com.testSuite.stevenramzel.testsuite.R;

public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo solo;
    final static String TAG = LoginActivityTest.class.getName();
    public LoginActivityTest() {
        super(LoginActivity.class);
        Log.i(TAG, "Instantiated...");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testLoginActivity(){
        final String fieldValue = "testSuite";

        Spoon.screenshot(getActivity(), "initial_state");

        // Set a value into the text field
        EditText editText = (EditText) solo.getView(R.id.email);
        solo.waitForView(editText);
        solo.enterText(editText, fieldValue);

        Spoon.screenshot(getActivity(), "before_button_clicked");

        // find button and click it
        Button button = (Button) solo.getView(R.id.email_sign_in_button);
        solo.waitForView(button);
        solo.clickOnView(button);

        // Wait 2 seconds for the start of the activity
        solo.waitForActivity(LoginActivity.class, 2000);
        solo.assertCurrentActivity("Should still be this activity", LoginActivity.class);

        Spoon.screenshot(solo.getCurrentActivity(), "second_state");
    }
    @Override
    protected void tearDown() throws Exception{
        try {
            solo.finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        getActivity().finish();
        super.tearDown();
    }
}