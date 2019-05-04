package lesson8_2.l8Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class l8ClientWindow extends JFrame {
    public l8ClientWindow() throws IOException {
        setTitle("Сетевой чат.");
        setBounds(200,200, 350, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(); // отображение всех сообщений
        textArea.setEditable(false);
        JTextField textField = new JTextField(); // ввод сообщения
        JButton button = new JButton("Отправить");
        JPanel panel = new JPanel(); // панель отправки сообщений
        panel.setLayout(new BorderLayout());

        add(textArea, BorderLayout.CENTER);
        panel.add(textField, BorderLayout.CENTER);
        panel.add(button, BorderLayout.EAST);
        add(panel, BorderLayout.SOUTH);

        // создаём сокет у клиента
        Socket clientSocket = new Socket("127.0.0.1", 8189);
        textArea.append("Вы подключились");
        System.out.println("Вы подключились");

        // создание исходящего потока
        DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());
        // создание потока чтения
        DataInputStream reader = new DataInputStream(clientSocket.getInputStream());

        writer.writeUTF("Клиент: дайте мне информацию"); // написание сообщение
        writer.flush(); // отправка

        Thread recievThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    try {
                        String str = reader.readUTF();
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                textArea.append(str);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        recievThread.start();

        writer.close(); // закрытие исходящего потока
        reader.close(); // закрытие чтения входящих
        System.out.println("Закрытие исходящего потока");
        System.out.println("Закрытие чтения входящих");

        clientSocket.close(); // закрытие сокета
        System.out.println("Закрытие сокета");

        setVisible(true);
    }
}
