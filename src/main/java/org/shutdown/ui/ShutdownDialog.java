package org.shutdown.ui;

import org.shutdown.core.ShutdownController;
import javax.swing.*;
import java.awt.*;

public class ShutdownDialog {
    private final JDialog dialog;
    private final JLabel timerLabel;
    private final ShutdownController controller;

    public ShutdownDialog(ShutdownController controller) {
        this.controller = controller;
        this.dialog = createDialog();
        this.timerLabel = new JLabel("", SwingConstants.CENTER);
        initializeComponents();
    }

    public static void showErrorDialog(String message, Exception ex) {
        JOptionPane.showMessageDialog(
                null,
                message + ex.getMessage(),
                "Eroare",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private JDialog createDialog() {
        JDialog createJDialogSample = new JDialog();
        createJDialogSample.setTitle("Confirmare Shutdown");
        createJDialogSample.setModal(true);
        createJDialogSample.setSize(350, 200);
        createJDialogSample.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        createJDialogSample.setLocationRelativeTo(null);
        return createJDialogSample;
    }

    private void initializeComponents() {
        dialog.setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));

        JLabel mainLabel = new JLabel("Doriți să închideți sistemul?", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        mainPanel.add(mainLabel);
        mainPanel.add(timerLabel);
        mainPanel.add(createButtonPanel());

        dialog.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton("YES");
        JButton noButton = new JButton("NO");

        yesButton.addActionListener(e -> controller.onUserResponse(true));
        noButton.addActionListener(e -> controller.onUserResponse(false));

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        return buttonPanel;
    }

    public void updateTimerDisplay(int seconds) {
        // Asigurăm că actualizarea se face pe thread-ul UI
        SwingUtilities.invokeLater(() -> timerLabel.setText(String.format("Timp rămas: %02d:%02d", seconds / 60, seconds % 60)));
    }

    public void showDialog() {
        dialog.setVisible(true);
    }

    public void closeDialog() {
        dialog.dispose();
    }
}