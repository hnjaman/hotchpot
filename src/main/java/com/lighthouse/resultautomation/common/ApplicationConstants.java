package com.lighthouse.resultautomation.common;

public class ApplicationConstants {
    private ApplicationConstants(){}

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String VALID_EMAIL_ADDRESS_REGEX = "^.+@[^-\\.].*\\.[a-z]{2,}$";
    public static final String VALID_PHONE_REGEX = "^\\+(?:[0-9]-?){6,14}[0-9]$";
    public static final String VALID_PASSWORD_REGEX = "(?=^.{8,}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s)[0-9a-zA-Z!@#$%^&*()]*$";
    public static final String VALID_IMAGE_URL_REGEX = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
    public static final String VALID_VIDEO_URL_REGEX = "^.*\\.(avi|AVI|wmv|WMV|flv|FLV|mpg|MPG|mp4|MP4)$";
    public static final String VALID_LP_PAGE_NAME = "^[a-zA-Z0-9_-]+( [a-zA-Z0-9_-]+)*$";
    public static final String VALID_COMPANY_NAME = "^[a-zA-Z0-9_-]+( [a-zA-Z0-9_-]+)*$";

    public final class StringUtils {
        private StringUtils() {
        }

        public static final String COMMA = ",";
        public static final String COLON = ":";
        public static final String EQUAL = "=";
        public static final String SEMI_COLON = ";";
        public static final String LESS_EQUAL = "<=";
        public static final String BACK_SLASH = "/";
        public static final String HYPHEN = "-";
        public static final String UNDER_SCORE = "_";
        public static final String WHITESPACE_SEQUENCE = "\\s+";
        public static final String ASTERISK = "*";
        public static final String EMPTY_STRING = "";
    }

    public final class SortOrder {
        private SortOrder() {
        }

        public static final String ASC = " asc";
        public static final String DESC = " desc";
    }

    public final class PasswordLength {
        public static final int MIN_LENGTH = 8;
        public static final int MAX_LENGTH = 32;
    }
}
