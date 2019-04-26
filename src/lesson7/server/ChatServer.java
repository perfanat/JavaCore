package lesson7.server;

import lesson7.client.AuthException;
import lesson7.client.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// основной класс сервера, включение
public class ChatServer {

    private AuthService authService = new AuthServiceImpl();
    private Map<String, ClientHandler> clientHandlerMap = Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start(7777);
    }

    private void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream inp = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("New client connected!");

                User user = null;
                try {
                    // считываем сообщение /auth
                    String authMessage = inp.readUTF();
                    // инициализируем юзера
                    user = checkAuthentication(authMessage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (AuthException ex) {
                    // пишем сообщение по ошибке авторизации
                    out.writeUTF("/auth fails");
                    // отправляем сообщение
                    out.flush();
                    // закрываем сокет
                    socket.close();
                }
                // если юзер не пустой и проверка authUser - true
                if (user != null && authService.authUser(user)) {
                    System.out.printf("User %s authorized successful!%n", user.getLogin());
                    clientHandlerMap.put(user.getLogin(), new ClientHandler(user.getLogin(), socket, this));
                    out.writeUTF("/auth successful");
                    out.flush();
                } else {
                    if (user != null) {
                        System.out.printf("Wrong authorization for user %s%n", user.getLogin());
                    }
                    out.writeUTF("/auth fails");
                    out.flush();
                    socket.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // провера аутентификации по сообщению auth
    private User checkAuthentication(String authMessage) throws AuthException {
        // получаем массив, разделяем сообщение auth пробелами
        String[] authParts = authMessage.split(" ");
        // если количество частей не 3 или первое слово не равно /auth, то ошибка
        if (authParts.length != 3 || !authParts[0].equals("/auth")) {
            System.out.printf("Incorrect authorization message %s%n", authMessage);
            // создаём ошибку
            throw new AuthException();
        }
        // создаём юзера с логином и паролем из массива
        return new User(authParts[1], authParts[2]);
    }

    public void sendMessage(String userTo, String userFrom, String msg) throws IOException {
        // получаем ClientHandler, кому отправлено сообщение
        ClientHandler userToClientHandler = clientHandlerMap.get(userTo);
        // TODO убедиться, что userToClientHandler существует и отправить сообщение
        // TODO для отправки сообщения нужно вызвать метод userToClientHandler.sendMessage()
        userToClientHandler.sendMessage(userFrom, msg);
    }
}