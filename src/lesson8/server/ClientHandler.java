package lesson8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import static lesson7.client.MessagePatterns.MESSAGE_SEND_PATTERN;

// класс, отвечающий за обмен сообщениями между клиентами и сервером
public class ClientHandler {

    private final String login;
    private final Socket socket;
    private final DataInputStream inp;
    private final DataOutputStream out;
    private final Thread handleThread;
    private ChatServer chatServer;

    public ClientHandler(String login, Socket socket, ChatServer chatServer) throws IOException {
        this.login = login;
        this.socket = socket;
        this.inp = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        this.chatServer = chatServer;

        this.handleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // пока поток не прерван
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        // получаем сообщение
                        String msg = inp.readUTF();
                        // печатаем сообщение
                        System.out.printf("Message from user %s: %s%n", login, msg);

                        // TODO проверить является ли msg сообщением для пользователя

                        ArrayList<String> authParts = new  ArrayList<String>(Arrays.asList(msg.split(" ")));
                        if (authParts.get(0).equals("/w")) {


                        // TODO если да, то переслать это сообщение пользователю
                        String userTo = authParts.get(1);
                        authParts.remove(1);
                        authParts.remove(0);
                        String message = authParts.toString();
                        //sendMessage(userTo, message);
                        chatServer.sendMessage(userTo, login, message);}

                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });
        this.chatServer = chatServer;
        this.handleThread.start();
    }

    public String getLogin() {
        return login;
    }

    public void sendMessage(String userFrom, String msg) throws IOException {
       out.writeUTF(String.format(MESSAGE_SEND_PATTERN, userFrom, msg));
    }
}
