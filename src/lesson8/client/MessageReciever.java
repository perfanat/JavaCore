package lesson8.client;

// интерфейс отправки сообщений
public interface MessageReciever {

    void submitMessage(TextMessage message);

    void userConnected(String login);

    void userDisconnected(String login);
}