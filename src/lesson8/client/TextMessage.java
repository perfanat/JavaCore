package lesson8.client;

import java.time.LocalDateTime;

// формат сообщения
public class TextMessage {

    private LocalDateTime created; // класс времени

    private String userFrom;

    private String userTo;

    private String text;

    public TextMessage(String userFrom, String userTo, String text) {
        this.created = LocalDateTime.now(); // время
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.text = text;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
