package lesson7.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import static lesson7.client.MessagePatterns.AUTH_PATTERN;
import static lesson7.client.MessagePatterns.MESSAGE_SEND_PATTERN;

// сеть
public class Network {

    public Socket socket;
    public DataInputStream in;
    public DataOutputStream out;

    private String hostName;
    private int port;
    private MessageReciever messageReciever; // получение сообщений

    private String login;

    // поток получения
    private Thread receiverThread;

    public Network(String hostName, int port, MessageReciever messageReciever) {
        this.hostName = hostName;
        this.port = port;
        this.messageReciever = messageReciever;

        this.receiverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String text = in.readUTF();

                        // TODO проверить, пришло ли в строке text сообщение
                        if (text!=null)
                        // TODO определить текст и отправителя
                        {
                            ArrayList<String> authParts = new ArrayList<String>(Arrays.asList(text.split(" ")));
                            String userFrom = authParts.get(1);
                            String userTo = login;
                            authParts.remove(1);
                            authParts.remove(0);
                            String msg="";
                            for (int i=0; i<authParts.size();i++){
                                msg+=authParts.get(i)+" ";
                            }
                            TextMessage textMessage = new TextMessage(userFrom, userTo, msg);
                            messageReciever.submitMessage(textMessage);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (socket.isClosed()) {
                            break;
                        }
                    }
                }
            }
        });
    }

    // метод авторизации
    public void authorize(String login, String password) throws IOException, AuthException {
        socket = new Socket(hostName, port);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());

        // отправка сообщения /auth
        sendMessage(String.format(AUTH_PATTERN, login, password));
        // получаем ответ
        String response = in.readUTF();
        if (response.equals("/auth successful")) {
            this.login = login;
            receiverThread.start();
        } else {
            throw new AuthException();
        }
    }

    // создание просто сообщения
    public void sendTextMessage(TextMessage message) {
        sendMessage(String.format(MESSAGE_SEND_PATTERN, message.getUserTo(), message.getText()));
    }

    // отправка сообщения
    private void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // берём логин в MainWindow
    public String getLogin() {
        return login;
    }
}