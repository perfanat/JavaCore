package lesson8_2.l8Client;

import javax.swing.*;
import java.io.*;

public class l8Client {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new l8ClientWindow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
