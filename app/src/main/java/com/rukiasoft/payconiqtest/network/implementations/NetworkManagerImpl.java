package com.rukiasoft.payconiqtest.network.implementations;

import com.rukiasoft.payconiqtest.network.NetworkManager;
import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

public class NetworkManagerImpl implements NetworkManager {

    @Inject
    LoggerHelper log;

    @Inject
    public NetworkManagerImpl() {
    }

    @Override
    public boolean isNetworkAvailable() {
        return false;
    }
}
