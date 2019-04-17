package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7777)) {
            System.out.println("Сервер ожидает подключения!");
            Socket socket = serverSocket.accept();
            System.out.println("Кто-то подключился: " + socket.getInetAddress());

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.out.print("Введите сообщение > ");

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    Scanner scanner = new Scanner(System.in);
                    while (scanner.hasNextLine())
                    try {
                        System.out.print("Введите сообщение > ");
                        String line = scanner.nextLine();
                        out.writeUTF(line);
                    } catch (IOException ex){
                        ex.printStackTrace();
                    }
                }
            });
            t1.start();


//            while (scanner.hasNextLine()) {
//                System.out.print("Введите сообщение > ");
//                String line = scanner.nextLine();
//                out.writeUTF(line);
//            }

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            System.out.println("Новое сообщение > " + in.readUTF());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            break;
                        }
                    }
                }
            });
            t2.start();

//            while (true) {
//                try {
//                    System.out.println("Новое сообщение > " + in.readUTF());
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                    break;
//                }
//            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
