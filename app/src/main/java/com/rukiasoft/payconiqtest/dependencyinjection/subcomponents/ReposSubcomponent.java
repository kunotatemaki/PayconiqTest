package com.rukiasoft.payconiqtest.dependencyinjection.subcomponents;

import com.rukiasoft.payconiqtest.dependencyinjection.modules.ReposModule;
import com.rukiasoft.payconiqtest.repolist.ui.activities.ReposActivity;

import java.util.ArrayList;

import dagger.Subcomponent;

/**
 * Created by Roll on 10/8/17.
 */

@Subcomponent(
        modules = {ReposModule.class}
)
public interface ReposSubcomponent {
    public void inject(ReposActivity activity);
}
