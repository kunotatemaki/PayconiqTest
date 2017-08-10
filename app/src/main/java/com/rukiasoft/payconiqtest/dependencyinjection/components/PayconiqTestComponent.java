package com.rukiasoft.payconiqtest.dependencyinjection.components;

import com.rukiasoft.payconiqtest.dependencyinjection.modules.NetworkModule;
import com.rukiasoft.payconiqtest.dependencyinjection.modules.PayconiqTestModule;
import com.rukiasoft.payconiqtest.dependencyinjection.modules.ReposModule;
import com.rukiasoft.payconiqtest.dependencyinjection.subcomponents.ReposSubcomponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Roll on 10/8/17.
 */

@Singleton
@Component(
        modules = {PayconiqTestModule.class, NetworkModule.class}
)
public interface PayconiqTestComponent {
    ReposSubcomponent getReposSubcomponent(ReposModule module);
}
