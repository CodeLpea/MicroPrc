package com.example.lp.httpprc;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.lp.httpprc.bean.testbean;
import com.example.lp.lphttp.LpHttp;
import com.example.lp.lphttp.response.LpResponseDataListener;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG="MainActivity";
    private String Url="http://www.kuaidi100.com/query?type=yuantong&postid=11111111111";

    @Test
    public void useAppContext() {
        // Context of the app under test.
  /*      Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.lp.httpprc", appContext.getPackageName());*/
        testIHttp();
    }

    private void testIHttp() {
        LpHttp.sendJsonRequest(null, Url, testbean.class, new LpResponseDataListener() {
            @Override
            public void onSuccess(Object clazz) {
                Log.e(TAG, "onSuccess:clazz "+clazz.toString()) ;
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
