package com.medicamp.sec;


public class SecConstants {
    public static final String SECRET = "SUPERDUPERSeCurEsECREt";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/auth/register";
    public static final String LOGIN_URL = "/api/auth/login";
    public static final String WELCOME_URL = "/";
}