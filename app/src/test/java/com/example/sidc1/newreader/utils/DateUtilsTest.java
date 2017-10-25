package com.example.sidc1.newreader.utils;

import com.example.sidc1.newreader.util.DateUtils;
import com.google.firebase.crash.FirebaseCrash;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * Created by sidc1 on 16-10-2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FirebaseCrash.class)
public class DateUtilsTest {
    String correctInputDate1 = "2017-10-16T09:56:27Z";
    String correctOutputDate1 = "Mon, 16 Oct 2017 09:56";
    String incorrectInputDate1 = "2017-10-16T09";
    @Test
    public void formatNewsApiDate_correctDate_outputsCorrectDate(){
        String outputDate = DateUtils.formatNewsApiDate(correctInputDate1);
        assertEquals(outputDate, correctOutputDate1);
    }

    @Test
    public void formatNewsApiDate_nullInput_outputsNull(){
        String outputDate = DateUtils.formatNewsApiDate(null);
        assertEquals(outputDate, null);
    }

    @Test
    public void formatNewsApiDate_incorrectInput_returnsSame(){
        PowerMockito.mockStatic(FirebaseCrash.class);
        String outputDate = DateUtils.formatNewsApiDate(incorrectInputDate1);
        assertEquals(outputDate, incorrectInputDate1);

        PowerMockito.verifyStatic();
        FirebaseCrash.report(Matchers.isA(ParseException.class));
    }
}
