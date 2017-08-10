package com.rukiasoft.payconiqtest.utils.logger.implementation;

import android.util.Log;

import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Roll on 10/8/17.
 */
@Singleton
public class AndroidLogHelperImpl implements LoggerHelper {

    private static final String LOG_PREFIX = "rukia_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    @Inject
    AndroidLogHelperImpl() {
    }


    @Override
    public void d(Object theClass, Object... messages) {
        String tag = makeLogTag(theClass);
        log(tag, Log.DEBUG, null, messages);
    }

    @Override
    public void e(Object theClass, Object... messages) {
        String tag = makeLogTag(theClass);
        log(tag, Log.ERROR, null, messages);
    }

    @Override
    public void i(Object theClass, Object... messages) {
        String tag = makeLogTag(theClass);
        log(tag, Log.INFO, null, messages);
    }

    @Override
    public void v(Object theClass, Object... messages) {
        String tag = makeLogTag(theClass);
        log(tag, Log.VERBOSE, null, messages);
    }

    @Override
    public void w(Object theClass, Object... messages) {
        String tag = makeLogTag(theClass);
        log(tag, Log.WARN, null, messages);
    }

    private static String makeLogTag(Object object) {
        String str = object.getClass().getSimpleName();
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    private static void log(String tag, int level, Throwable t, Object... messages) {
        if (Log.isLoggable(tag, level)) {
            String message;
            if (t == null && messages != null && messages.length == 1) {
                // handle this common case without the extra cost of creating a stringbuffer:
                message = messages[0].toString();
            } else {
                StringBuilder sb = new StringBuilder();
                if (messages != null) for (Object m : messages) {
                    sb.append(m);
                }
                if (t != null) {
                    sb.append("\n").append(Log.getStackTraceString(t));
                }
                message = sb.toString();
            }
            Log.println(level, tag, message);
        }
    }
}
