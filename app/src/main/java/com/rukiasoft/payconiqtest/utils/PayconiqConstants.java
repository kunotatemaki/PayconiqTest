package com.rukiasoft.payconiqtest.utils;

/**
 * Created by Roll on 11/8/17.
 */

public class PayconiqConstants {
    public enum StatusResponse{
        ORIGINAL_STATE,
        DOWNLOAD_OK,
        NO_MORE_REPOS,
        DOWNLOAD_FAILED
    }

    public static final String BASE_URL = "https://api.github.com/";
    public static final String NICKNAME = "JakeWharton";
    public static final int PER_PAGE_VALUE = 15;
}
