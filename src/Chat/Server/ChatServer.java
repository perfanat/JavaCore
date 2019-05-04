package Chat.Server;

import Chat.network.TCPConnection;
import Chat.network.TCPConnectionListener;

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
        System.out.println("l8Server running...");
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            while (true){
                try {
                    new TCPConnection(this, serverSocket.accept());
                } catch (IOException e){
                    System.out.println("TCPConnection exception: "+e);
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onConnecrionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnections("l8ClientWindow connected: "+tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        sendToAllConnections(value);
    }

    @Override
    public synchronized void onDisconnected(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnections("l8ClientWindow disconnected: "+tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection: "+ e);
    }

    private void sendToAllConnections (String value){
        System.out.println(value);
        final int cnt = connections.size();
        for (int i = 0; i < cnt; i++) {
            connections.get(i).sendString(value);
        }
    }
}
