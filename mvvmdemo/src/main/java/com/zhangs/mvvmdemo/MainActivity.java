package com.zhangs.mvvmdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhangs.mvvmdemo.bean.UserInfo;
import com.zhangs.mvvmdemo.databinding.ActivityMainBinding;
import com.zhangs.mvvmdemo.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding=DataBindingUtil.setContentView(this,R.layout.activity_main);
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName("sjljl");
        userInfo.setUserAge(54);
        userInfo.setUserProfile("545113safdafadfs");
        binding.setViewModel(new MainViewModel(binding));
    }
}
