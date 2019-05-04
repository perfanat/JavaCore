package lesson8_Chat.client;

import lesson8_Chat.network.TCPConnection;
import lesson8_Chat.network.TCPConnectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientWindow extends JFrame implements ActionListener, TCPConnectionListener {

    private static final String IP_ADDR = "localhost";
    private static final int PORT = 8189;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientWindow();
            }
        });
    }

    private final JTextArea log = new JTextArea();
    private final JTextField fieldNickname = new JTextField("Иван");
    private final JTextField fieldInput = new JTextField();

    private TCPConnection connection;

    private ClientWindow (){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        log.setEditable(false);
        log.setLineWrap(true);
        fieldInput.addActionListener(this);
        add(log, BorderLayout.CENTER);
        add (fieldNickname, BorderLayout.NORTH);
        add (fieldInput, BorderLayout.SOUTH);

        setVisible(true);

        try {
            connection = new TCPConnection(this, IP_ADDR, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        if (msg.equals(""))return;
        fieldInput.setText(null);
        connection.sendString(fieldNickname.getText()+": "+msg);
    }

    @Override
    public void onConnecrionReady(TCPConnection tcpConnection) {
        printMsg("Вы подключились");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMsg(value);
    }

    @Override
    public void onDisconnected(TCPConnection tcpConnection) {
        printMsg("Вы отключились");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMsg("Ошибка: "+e);
    }

    private synchronized void printMsg (String msg){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg+"\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}
