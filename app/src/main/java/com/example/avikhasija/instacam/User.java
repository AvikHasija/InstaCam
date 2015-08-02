package com.example.avikhasija.instacam;

import android.util.Log;

import com.facebook.model.GraphObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Avik Hasija on 8/2/2015.
 */
public class User {

    private static final String TAG = "User";

    private String mFirstName;
    private String mLastName;
    private Date mBirthday;
    private String mAvatarURL;

    User(GraphObject graphObject){

        mFirstName = (String) graphObject.getProperty("first_name");
        mLastName = (String) graphObject.getProperty("last_name");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            mBirthday = simpleDateFormat.parse((String)graphObject.getProperty("birthday"));
        } catch (ParseException e) {
            Log.d(TAG, "Failed at parsing date " + graphObject.getProperty("birthday"));
        }

        mAvatarURL = (String) graphObject.getPropertyAs("picture", GraphObject.class)
                .getPropertyAs("data", GraphObject.class)
                .getProperty("url");
    }
}
