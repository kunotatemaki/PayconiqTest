package com.rukiasoft.payconiqtest.splash.activities;

import android.content.Intent;
import android.os.Bundle;

import com.rukiasoft.payconiqtest.repolist.ui.mainviews.ReposActivity;
import com.rukiasoft.payconiqtest.utils.ui.BaseActivity;


public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launchMainScreen();
    }

    private void launchMainScreen(){
        Intent intent = new Intent(this, ReposActivity.class);
        startActivity(intent);
        finish();
    }

}