package com.zhangs.mvvmdemo.bean;

import android.databinding.BaseObservable;

public class UserInfo extends BaseObservable{
    private String userName;
    private int userAge;
    private String userProfile;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }
}
