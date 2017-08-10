package com.rukiasoft.payconiqtest;

import android.app.Application;

import com.rukiasoft.payconiqtest.dependencyinjection.components.DaggerPayconiqTestComponent;
import com.rukiasoft.payconiqtest.dependencyinjection.components.PayconiqTestComponent;
import com.rukiasoft.payconiqtest.dependencyinjection.modules.PayconiqTestModule;

/**
 * Created by Roll on 10/8/17.
 */

public class PayconiqApplication extends Application {
    private PayconiqTestComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = DaggerPayconiqTestComponent.builder()
                .payconiqTestModule(new PayconiqTestModule(this))
                .build();
    }

    public PayconiqTestComponent getmComponent() {
        return mComponent;
    }
}
