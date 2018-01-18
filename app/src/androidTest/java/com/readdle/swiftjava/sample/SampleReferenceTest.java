package com.readdle.swiftjava.sample;

import com.readdle.codegen.anotation.JavaSwift;
import com.readdle.codegen.anotation.SwiftError;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SampleReferenceTest {

    private SampleReference sampleReference;

    @Before
    public void setUp() {
        System.loadLibrary("SampleAppCoreBridge");
        JavaSwift.init();

        this.sampleReference = SampleReference.init();
    }

    @After
    public void clear() {
        this.sampleReference.release();
    }

    @Test
    public void testGetRandomValue() {
        Assert.assertNotNull(sampleReference.getRandomValue());
    }

    @Test
    public void testSaveValue() {
        SampleValue sampleValue = new SampleValue();
        sampleValue.str1 = "str1";
        sampleValue.str2 = "str2";
        sampleValue.str3 = "str3";
        sampleReference.saveValue(sampleValue);
    }

    @Test
    public void testThrows() {
        try {
            sampleReference.funcThrows();
            Assert.fail();
        } catch (SwiftError swiftError) {
            Assert.assertTrue(swiftError.getMessage() != null);
        }
    }

    @Test
    public void testNil() {
        Assert.assertNull(sampleReference.funcWithNil());
    }

    @Test
    public void testDelegate() {
        final boolean[] isFlag = new boolean[1];
        SampleDelegateAndroid delegateAndroid = new SampleDelegateAndroid() {

            @Override
            void onSetSampleValue(SampleValue value) {
                isFlag[0] = true;
            }
        };
        sampleReference.setDelegate(delegateAndroid);
        Assert.assertTrue(System.currentTimeMillis() - sampleReference.tick() < 1000);
        Assert.assertTrue(delegateAndroid.sampleValue.equals(sampleReference.getRandomValue()));
        Assert.assertTrue(isFlag[0]);
        Assert.assertNull(sampleReference.funcWithNil());
        delegateAndroid.release();
    }

    @Test
    public void testDelegateBlock() {
        final boolean[] isFlag = new boolean[1];
        sampleReference.tickWithBlock(new SampleReference.SampleInterfaceDelegateAndroid() {
            @Override
            public void onCall() {
                isFlag[0] = true;
            }
        });
        Assert.assertTrue(isFlag[0]);
    }

    @Test
    public void testFloatingPointer() {
        Assert.assertTrue(sampleReference.floatCheck(1.0f) == 3.0f);
        Assert.assertTrue(sampleReference.doubleCheck(1.0) == 3.0);
    }

    @Test
    public void testException() {
        Exception exception1 = new Exception("");
        Exception exception2 = new Exception("QWERTY");
        Exception exception3 = new Exception("QWERTY:1");
        Assert.assertTrue(sampleReference.exceptionCheck(exception1).getMessage().equals("JavaException:0"));
        Assert.assertTrue(sampleReference.exceptionCheck(exception2).getMessage().equals(exception2.getMessage() + ":0"));
        Assert.assertTrue(sampleReference.exceptionCheck(exception3).getMessage().equals(exception3.getMessage()));
    }

    @Test
    public void testEnclose() {
        Assert.assertNotNull(sampleReference.enclose(SampleReference.SampleReferenceEnclose.init()));
    }

}
