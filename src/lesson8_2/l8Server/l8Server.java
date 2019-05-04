package lesson8_2.l8Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class l8Server {
    public static void main(String[] args) throws IOException {
        int count = 0;
        ServerSocket serverSocket = new ServerSocket(8189); // создание сервера
        System.out.println("Сервер запущен...");

        while (true) {
            Socket clientSocket = serverSocket.accept(); // ожидание юзера и создание сокета
            System.out.println("Подключился клиент №"+(++count));

            // создание потока чтения
            DataInputStream reader = new DataInputStream(clientSocket.getInputStream());
            // создание исходящего потока
            DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());

            writer.writeUTF("Сервер: Вы клиент №"+count); // написание сообщение

            Thread recievThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.currentThread().isInterrupted()){
                        try {
                            System.out.println(reader.readUTF());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            recievThread.start();

            System.out.println("Закрытие исходящего потока");
            System.out.println("Закрытие чтения входящих");
            writer.close(); // закрытие исходящего потока
            reader.close(); // закрытие чтения входящих

            System.out.println("Закрытие сокета");
            clientSocket.close(); // закрытие сокета
        }
    }
}
