package com.rukiasoft.payconiqtest.dependencyinjection.subcomponents;

import com.rukiasoft.payconiqtest.dependencyinjection.modules.ReposModule;
import com.rukiasoft.payconiqtest.dependencyinjection.scopes.CustomScopes;
import com.rukiasoft.payconiqtest.repolist.ui.mainviews.ReposActivity;

import dagger.Subcomponent;

/**
 * Created by Roll on 10/8/17.
 */
@CustomScopes.ActivityScope
@Subcomponent(
        modules = {ReposModule.class}
)
public interface ReposSubcomponent {
    void inject(ReposActivity activity);
}
