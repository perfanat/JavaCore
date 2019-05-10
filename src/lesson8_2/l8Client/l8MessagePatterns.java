package lesson8_2.l8Client;

public class l8MessagePatterns {

    // сообщение авторизации
    public static final String AUTH_PATTERN = "/auth %s %s";
    public static final String AUTH_SUCCESS_RESPONSE = "/auth successful";
    public static final String AUTH_FAIL_RESPONSE = "/auth fail";
}
