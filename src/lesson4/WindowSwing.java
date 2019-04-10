package lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WindowSwing extends JFrame {

    private String text1 = "";
    private String text2 = "";

    private WindowSwing(){
        // настройки окна
        setTitle("Окно чата");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 120, 355, 512);
        setLayout(new BorderLayout());

        // элементы окна
        JTextArea textArea1 = new JTextArea();
        textArea1.setEditable(false);
        JTextArea textArea2 = new JTextArea("", 3,26);
        textArea2.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!textArea2.getText().equals("")) {
                        text2 = textArea2.getText();
                        textArea1.setText(text1 += "Пользователь: "+ text2+"\n----------\n");
                        textArea2.setText("");
                    }
                }
            }
        });
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        JScrollPane scroll1 = new JScrollPane(textArea1,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scroll2 = new JScrollPane(textArea2,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JButton button1 = new JButton("OK");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textArea2.getText().equals("")) {
                    text2 = textArea2.getText();
                    textArea1.setText(text1 += "Пользователь: "+ text2+"\n----------\n");
                    textArea2.setText("");
                    textArea2.requestFocus();
                }
            }
        });
        button1.setPreferredSize(new Dimension(60,50));
        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout ());
        add(scroll1, BorderLayout.CENTER);
        panel.add(scroll2);
        panel.add(button1);

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new WindowSwing();
    }
}
