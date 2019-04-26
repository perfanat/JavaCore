package lesson7.client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// диалоговое окно авторизации клиента
public class LoginDialog extends JDialog {

    private Network network;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;

    private boolean connected;

    public LoginDialog(Frame parent, Network network) {
        super(parent, "Логин", true);
        this.network = network;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Имя пользователя: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Пароль: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
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
                    // авторизация в Network
                    network.authorize(tfUsername.getText(), String.valueOf(pfPassword.getPassword()));
                    connected = true;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Ошибка сети",
                            "Авторизация",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (AuthException ex) {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Ошибка авторизации",
                            "Авторизация",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dispose(); // закрываем окно
            }
        });

        bp.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connected = false;
                dispose();
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack(); // задаёт оптимальные размеры окна
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public boolean isConnected() {
        return connected;
    }
}
