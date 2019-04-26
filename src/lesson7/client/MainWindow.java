package lesson7.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

// основное окно с интерфейсом MessageReciever (отправка сообщений)
public class MainWindow extends JFrame implements MessageReciever {

    // список для отображения данных
    private final JList<TextMessage> messageList;

    // модель списка для заполнения
    private final DefaultListModel<TextMessage> messageListModel;

    // класс отображения элементов списка
    private final TextMessageCellRenderer messageCellRenderer;

    // покрутка
    private final JScrollPane scroll;

    private final JPanel sendMessagePanel;

    private final JButton sendButton;

    private final JTextField messageField;
    private final JTextField messageFieldToUser;

    private final Network network;


    public MainWindow() {
        setTitle("Application");
        setBounds(200,200, 500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        messageList = new JList<>();
        messageListModel = new DefaultListModel<>();
        messageCellRenderer = new TextMessageCellRenderer();
        messageList.setModel(messageListModel);
        messageList.setCellRenderer(messageCellRenderer);

        scroll = new JScrollPane(messageList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);

        sendMessagePanel = new JPanel();
        sendMessagePanel.setLayout(new BorderLayout());
        sendButton = new JButton("Отправить");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // получаем текст ввода
                String text = messageField.getText();

                String userTo = null;
                boolean isUserTo = false;

                // если поле ввода адресата пустое
                if (messageFieldToUser.getText().equals("")){
                    ArrayList<String> authParts = new  ArrayList<String>(Arrays.asList(text.split(" ")));
                    if (authParts.get(0).equals("/w")) {
                        userTo=authParts.get(1);
                        isUserTo=true;
                        authParts.remove(1);
                        authParts.remove(0);
                        String msg="";
                        for (int i=0; i<authParts.size();i++){
                            msg+=authParts.get(i)+" ";
                        }
                        text=msg;
                    }
                } else {
                    userTo=messageFieldToUser.getText();
                    isUserTo=true;
                }

                // если ничего не написано или без пробелов оно пустое
                if (text != null && !text.trim().isEmpty()&&isUserTo) {
                    TextMessage msg = new TextMessage(network.getLogin(), userTo, text);
                    messageListModel.add(messageListModel.size(), msg);
                    messageField.setText(null);

                    // TODO реализовать проверку, что сообщение не пустое
                    network.sendTextMessage(msg);
                } else {
                    if (!isUserTo){
                        JOptionPane.showMessageDialog(MainWindow.this,
                                "Не выбран пользователь",
                                "Отправка сообщения",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
        });
        sendMessagePanel.add(sendButton, BorderLayout.EAST);
        messageField = new JTextField();
        messageFieldToUser = new JTextField(8);

        sendMessagePanel.add(messageFieldToUser, BorderLayout.WEST);
        sendMessagePanel.add(messageField, BorderLayout.CENTER);

        add(sendMessagePanel, BorderLayout.SOUTH);
        setVisible(true);

        this.network = new Network("localhost", 7777, this);

        LoginDialog loginDialog = new LoginDialog(this, network);
        loginDialog.setVisible(true);

        // если мы не авторизовываемся то выходим
        if (!loginDialog.isConnected()) {
            System.exit(0);
        }
    }

    // реализация интерфейса
    @Override
    public void submitMessage(TextMessage message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                messageListModel.add(messageListModel.size(), message);
                messageList.ensureIndexIsVisible(messageListModel.size() - 1);
            }
        });
    }
}
