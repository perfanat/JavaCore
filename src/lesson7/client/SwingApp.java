package lesson7.client;

import javax.swing.*;

// запуск клиента
public class SwingApp {

    private static MainWindow mainWindow;

    public static void main(String[] args) {
        // запускаем основное окно, чтобы оно не прерывалось
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainWindow = new MainWindow();
            }
        });
    }
}