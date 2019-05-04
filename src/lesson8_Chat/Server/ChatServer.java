package lesson8_Chat.Server;

import lesson8_Chat.network.TCPConnection;
import lesson8_Chat.network.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

// создание сервера
public class ChatServer implements TCPConnectionListener {

    // запуск сервера
    public static void main(String[] args) {
        new ChatServer();
    }

    private final ArrayList <TCPConnection> connections = new ArrayList<>();

    private ChatServer (){
        System.out.println("Сервер запущен...");
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            while (true){
                try {
                    new TCPConnection(this, serverSocket.accept());
                } catch (IOException e){
                    System.out.println("Ошибка: "+e);
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onConnecrionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnections("Подключился: "+tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        sendToAllConnections(value);
    }

    @Override
    public synchronized void onDisconnected(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnections("Отключился: "+tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("Ошибка: "+ e);
    }

    private void sendToAllConnections (String value){
        System.out.println(value);
        final int cnt = connections.size();
        for (int i = 0; i < cnt; i++) {
            connections.get(i).sendString(value);
        }
    }
}
