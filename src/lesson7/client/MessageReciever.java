package lesson7.client;

// интерфейс отправки сообщений
public interface MessageReciever {

    void submitMessage(TextMessage message);
}