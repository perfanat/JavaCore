package lesson8_2.l8Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class l8ClientWindow extends JFrame {

    public static JTextArea textArea;
    public JTextField textField;
    Socket clientSocket;

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

        setVisible(true);

        //Socket clientSocket = new Socket("127.0.0.1", 8189);

        l8LoginDialog dialog = new l8LoginDialog(this);
        dialog.setVisible(true);

        clientSocket = l8LoginDialog.clentSocket;

        textArea.append("Вы подключились\n");
        System.out.println("Вы подключились");

        // создание исходящего потока
        DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());
        // создание потока чтения
        DataInputStream reader = new DataInputStream(clientSocket.getInputStream());

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                try {
                    writer.writeUTF("Клиент: "+text);
                    textField.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                try {
                    writer.writeUTF("Клиент: "+text);
                    textField.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        String str = reader.readUTF();
        textArea.append(str+"\n");

        writer.writeUTF("Клиент: дайте мне информацию"); // написание сообщение
        writer.flush(); // отправка

        Thread receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    String str = null;
                    try {
                        str = reader.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (!str.equals(null)){
                        textArea.append(str+"\n");
                    }
                }
            }
        });
        receiveThread.start();
    }
}
