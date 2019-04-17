package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);
             Socket socket = new Socket("localhost", 7777)) {

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.out.print("Введите сообщение > ");

//            while (scanner.hasNextLine()) {
//                System.out.print("Введите сообщение > ");
//                String line = scanner.nextLine();
//                out.writeUTF(line);
//            }
//
//            while (true) {
//                try {
//                    System.out.println("Новое сообщение > " + in.readUTF());
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                    break;
//                }
//            }

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
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

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}