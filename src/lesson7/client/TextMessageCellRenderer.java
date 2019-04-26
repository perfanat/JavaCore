package lesson7.client;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

// класс для отрисовывания элементов списка с интерфейсом
public class TextMessageCellRenderer extends JPanel implements ListCellRenderer<TextMessage> {

    // формат времени
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    // поле надписи времени
    private final JLabel created;

    private final JLabel userName;

    private final JTextArea messageText;

    private final JPanel panel;

    public TextMessageCellRenderer() {
        super();
        setLayout(new BorderLayout());

        created = new JLabel();
        userName = new JLabel();
        messageText = new JTextArea();
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panel.add(created);
        panel.add(userName);

        // шрифт
        Font f = userName.getFont();
        userName.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        messageText.setLineWrap(true); // перенос слов
        messageText.setWrapStyleWord(true); // перенос длинных слов
        messageText.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(messageText, BorderLayout.SOUTH);
    }

    // переопределение интерфейса
    @Override
    public Component getListCellRendererComponent(JList<? extends TextMessage> list,
                                                  TextMessage value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        setBackground(list.getBackground());
        // время из TextMessage с форматом
        created.setText(value.getCreated().format(timeFormatter));
        // непрозрачность
        userName.setOpaque(true);
        userName.setText(value.getUserFrom()+" to "+value.getUserTo());
        messageText.setText(value.getText());
        return this;
    }
}