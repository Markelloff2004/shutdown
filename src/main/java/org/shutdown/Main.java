package org.shutdown;

import org.shutdown.core.ShutdownController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShutdownController controller = new ShutdownController();
            controller.startShutdownCountdown();
        });
    }
}