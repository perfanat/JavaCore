package lesson8_2.l8Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class l8Server {

    private static Map<String, Socket> listOfSockets = Collections.synchronizedMap(new HashMap<>());
    static int count = 0;
    public static l8AuthService authService;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8189); // создание сервера
        new l8AuthService();
        System.out.println("Сервер запущен...");

        while (true) {
            Socket clientSocket = serverSocket.accept(); // ожидание юзера и создание сокета
            System.out.println("Подключился клиент №"+(++count));

            // создание потока чтения
            DataInputStream reader = new DataInputStream(clientSocket.getInputStream());
            // создание исходящего потока
            DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());

            Thread receiveThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.currentThread().isInterrupted()){
                        String text = null;
                        try {
                            text = reader.readUTF();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                        System.out.println(text);
                        String[] parts = text.split(" ", 3);
                        if (parts[0].equals("/auth")){
                            l8User user = new l8User(parts[1], parts[2]);
                            if (l8AuthService.authUser (user)){
                                try {
                                    writer.writeUTF("/auth successful");
                                    listOfSockets.put(user.getLogin(), clientSocket);
                                    writer.writeUTF("Сервер: Вы клиент №"+count); // написание сообщение
                                    System.out.println("отправка: Сервер: Вы клиент №"+count);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    writer.writeUTF("/auth fail");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            System.out.println("отправить сообщение клиенту");
                        }
                    }
                }
            });
            receiveThread.start();
        }
    }
}
