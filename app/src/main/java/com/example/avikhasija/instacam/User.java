package com.example.avikhasija.instacam;

import android.util.Log;

import com.facebook.model.GraphObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Avik Hasija on 8/2/2015.
 */
public class User implements Serializable{

    private static final String TAG = "User";

    private String mFirstName;
    private String mLastName;
    private Date mBirthday;
    private String mAvatarURL;

    public Date getBirthday() {
        return mBirthday;
    }

    public String getAvatarURL() {
        return mAvatarURL;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    private static User sCurrentUser;

    public static User getCurrentUser() {
        return sCurrentUser;
    }

    public static void setCurrentUser(GraphObject graphObject) {
        if (sCurrentUser == null){
            sCurrentUser = new User(graphObject);
        }
    }

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
