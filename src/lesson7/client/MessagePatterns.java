
package lesson7.client;

// формат исходящих сообщений
public final class MessagePatterns {

    // сообщение аутентификации с /auth
    public static final String AUTH_PATTERN = "/auth %s %s";
    // сообщение просто с /w
    public static final String MESSAGE_SEND_PATTERN = "/w %s %s";
}