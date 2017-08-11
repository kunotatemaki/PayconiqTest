package com.rukiasoft.payconiqtest;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.rukiasoft.payconiqtest.dependencyinjection.components.DaggerPayconiqTestComponent;
import com.rukiasoft.payconiqtest.dependencyinjection.components.PayconiqTestComponent;
import com.rukiasoft.payconiqtest.dependencyinjection.modules.PayconiqTestModule;
import com.rukiasoft.payconiqtest.persistence.databases.PayconiqDatabase;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;

/**
 * Created by Roll on 10/8/17.
 */

public class PayconiqApplication extends Application {

    private PayconiqTestComponent mComponent;

    private PayconiqDatabase mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        //Dagger
        mComponent = DaggerPayconiqTestComponent.builder()
                .payconiqTestModule(new PayconiqTestModule(this))
                .build();

        //Room
        mDatabase = Room.databaseBuilder(getApplicationContext(),
                PayconiqDatabase.class, PayconiqConstants.DATABASE_NAME).build();
    }

    public PayconiqTestComponent getmComponent() {
        return mComponent;
    }

    public PayconiqDatabase getmDatabase() {
        return mDatabase;
    }
}
