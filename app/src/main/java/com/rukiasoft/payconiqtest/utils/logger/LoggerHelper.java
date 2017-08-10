package com.rukiasoft.payconiqtest.utils.logger;

import java.util.Objects;

/**
 * Created by Roll on 10/8/17.
 */

public interface LoggerHelper {

    void d(Object theClass, Object... messages);

    void e(Object theClass, Object... messages);

    void w(Object theClass, Object... messages);

    void i(Object theClass, Object... messages);

    void v(Object theClass, Object... messages);

}
