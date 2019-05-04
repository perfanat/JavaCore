package Chat.network;

public interface TCPConnectionListener {
    void onConnecrionReady (TCPConnection tcpConnection); // соединение установлено
    void onReceiveString (TCPConnection tcpConnection, String value); // пришло сообщение
    void onDisconnected (TCPConnection tcpConnection); // отключение
    void onException (TCPConnection tcpConnection, Exception e); // обработка исключений
}
