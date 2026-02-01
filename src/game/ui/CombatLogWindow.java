package game.ui;

import javax.swing.*;
import java.awt.*;

public class CombatLogWindow {

    private static JTextArea textArea;

    public static void init() {
        // Força uso de fonte lógica do Java
        UIManager.put("Label.font", new Font("Monospaced", Font.PLAIN, 12));
        UIManager.put("TextArea.font", new Font("Monospaced", Font.PLAIN, 12));

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Combat Log");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.add(scrollPane);

            frame.setVisible(true);
        });
    }

    public static void log(String msg) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(msg + "\n");
            textArea.setCaretPosition(textArea.getDocument().getLength());
        });
    }
}
