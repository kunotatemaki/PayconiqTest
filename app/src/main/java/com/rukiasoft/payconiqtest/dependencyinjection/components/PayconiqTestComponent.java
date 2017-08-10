package com.rukiasoft.payconiqtest.dependencyinjection.components;

import com.rukiasoft.payconiqtest.dependencyinjection.modules.PayconiqTestModule;
import com.rukiasoft.payconiqtest.dependencyinjection.modules.ReposModule;
import com.rukiasoft.payconiqtest.dependencyinjection.subcomponents.ReposSubcomponent;

import dagger.Component;

/**
 * Created by Roll on 10/8/17.
 */

@Component(
        modules = {PayconiqTestModule.class}
)
public interface PayconiqTestComponent {
    public ReposSubcomponent getReposSubcomponent(ReposModule module);
}
