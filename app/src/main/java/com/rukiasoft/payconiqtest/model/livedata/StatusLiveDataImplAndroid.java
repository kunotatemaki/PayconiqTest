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

public class StatusLiveDataImplAndroid extends MutableLiveData<PayconiqConstants.StatusResponse>
        implements CustomLivedata<PayconiqConstants.StatusResponse> {

    @Inject
    public StatusLiveDataImplAndroid() {
    }

    @Override
    public void setLivedataValue(PayconiqConstants.StatusResponse value) {
        this.setValue(value);
    }

    @Override
    public PayconiqConstants.StatusResponse getLivedataValue() {
        return this.getValue();
    }

   @Override
    public void addObserverToLivedata(ReposView view, final LivedataObserver observer) {
        if(view instanceof LifecycleRegistryOwner) {
            this.observe((LifecycleRegistryOwner) view, new Observer<PayconiqConstants.StatusResponse>() {
                @Override
                public void onChanged(@Nullable PayconiqConstants.StatusResponse status) {
                    observer.handleChangesInObservedStatus(status);
                }
            });
        }
    }
}
