package com.rukiasoft.payconiqtest.model.livedata;

import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.livedataobservers.LivedataObserver;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

public class StatusLiveDataImplAndroid extends MutableLiveData<PayconiqConstants.STATUS_RESPONSE>
        implements CustomLivedata<PayconiqConstants.STATUS_RESPONSE> {

    @Inject
    public StatusLiveDataImplAndroid() {
    }

    @Override
    public void setLivedataValue(PayconiqConstants.STATUS_RESPONSE value) {
        this.setValue(value);
    }

    @Override
    public PayconiqConstants.STATUS_RESPONSE getLivedataValue() {
        return this.getValue();
    }

   @Override
    public void addObserverToLivedata(ReposView view, final LivedataObserver observer) {
        if(view instanceof LifecycleRegistryOwner) {
            this.observe((LifecycleRegistryOwner) view, new Observer<PayconiqConstants.STATUS_RESPONSE>() {
                @Override
                public void onChanged(@Nullable PayconiqConstants.STATUS_RESPONSE status) {
                    observer.handleChangesInObservedStatus(status);
                }
            });
        }
    }
}
