package com.zhangs.mvvmdemo.viewmodel;

import android.view.View;

import com.zhangs.mvvmdemo.BR;
import com.zhangs.mvvmdemo.bean.UserInfo;
import com.zhangs.mvvmdemo.databinding.ActivityMainBinding;

import java.util.Random;

public class MainViewModel {

    private ActivityMainBinding binding;
    private UserInfo userInfo=new UserInfo();
    private Random random=new Random();

    private String[] userNames=new String[]{"张三","里斯","赵六","王五","沈七","旺财","小强","sdfs"};
    private int[]  userAges=new int[]{21,31,35,56,65,12,66,99};
    private String[] profiles=new String[]{"天行健，君子以自强不息","相信自己，你就是最棒的","你现在的努力都是为了以后的毫不费力","没有个性不签名","眼见不一定为实","just try it","a i u e o","sojoijsdjfsjl"};
    public MainViewModel(ActivityMainBinding binding){
        this.binding=binding;
        initUser();
    }

    public void updateUserInfo(View view){
        initUser();

//        binding.tvUserName.setText(userInfo.getUserName());
//        binding.tvUserAge.setText(userInfo.getUserAge()+"");
//        binding.tvUserProfile.setText(userInfo.getUserProfile());
    }

    private void initUser(){
        userInfo.setUserName(userNames[random.nextInt(8)]);
        userInfo.setUserAge(userAges[random.nextInt(8)]);
        userInfo.setUserProfile(profiles[random.nextInt(8)]);
        binding.setVariable(BR.userinfo,userInfo);
    }
}
