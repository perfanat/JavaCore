package lesson8_2.l8Client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static lesson8_2.l8Client.l8MessagePatterns.*;

public class l8LoginDialog extends JDialog {

    public static Socket clentSocket;
    private JLabel lbUsername;
    private JComboBox jComboBox;
    private JTextField pfPassword;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;

    public l8LoginDialog (l8ClientWindow parent){
        super(parent, "Авторизация");

        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Имя пользователя: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        String []arrUser = {"<html><font color = gray><i>выбор юзера</i ></font>","Иван", "Петр", "Юля"};
        Map<String,String> listOfUsers=new HashMap<>(Map.of("Иван", "1", "Петр", "2","Юля", "3"));
        jComboBox = new JComboBox(arrUser);
        jComboBox.setSize(5, 30);
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text=(String) jComboBox.getSelectedItem();
                pfPassword.setText(listOfUsers.get(text));
            }
        });

        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(jComboBox, cs);

        lbPassword = new JLabel("Пароль: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JTextField(10);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Войти");
        btnCancel = new JButton("Отмена");

        JPanel bp = new JPanel();
        bp.add(btnLogin);

        // кнопка подключения
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Socket clientSocket = new Socket("127.0.0.1", 8189);
                    Socket clientSocket = parent.clientSocket;
                    // создание исходящего потока
                    DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());
                    // создание потока чтения
                    DataInputStream reader = new DataInputStream(clientSocket.getInputStream());
                    writer.writeUTF(String.format(AUTH_PATTERN, jComboBox.getSelectedItem(), pfPassword.getText()));
                    String text = reader.readUTF();
                    if (text.equals(AUTH_SUCCESS_RESPONSE)){
                        dispose();
                    } else if (text.equals(AUTH_FAIL_RESPONSE)){
                        JOptionPane.showMessageDialog(l8LoginDialog.this,
                                "Ошибка авторизации",
                                "Авторизация",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                dispose(); // закрываем окно
            }
        });

        bp.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack(); // задаёт оптимальные размеры окна
        setResizable(false);
        setLocationRelativeTo(parent);

    }
}
