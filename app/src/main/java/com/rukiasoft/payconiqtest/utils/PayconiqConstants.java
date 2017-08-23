package com.rukiasoft.payconiqtest.utils;

/**
 * Created by Roll on 11/8/17.
 */

public class PayconiqConstants {
    public enum STATUS_RESPONSE {
        ORIGINAL_STATE(0),
        LOAD_OK(1),
        NO_MORE_REPOS(-1),
        LOAD_FAILED(-2);

        private int numVal;

        STATUS_RESPONSE(int val){
            this.numVal = val;
        }

        public int getNumVal(){
            return numVal;
        }
    }

    public static final String BASE_URL = "https://api.github.com/";
    public static final String NICKNAME = "JakeWharton";
    public static final int PER_PAGE_VALUE = 15;

    public static final String DATABASE_NAME = "payconiq-db";
}
