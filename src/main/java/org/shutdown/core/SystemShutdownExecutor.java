package org.shutdown.core;

import org.shutdown.ui.ShutdownDialog;

public class SystemShutdownExecutor {
    public static void shutdown() {
        try {
            Runtime.getRuntime().exec("shutdown -s -t 0");
            System.exit(0);
        } catch (Exception ex) {
            ShutdownDialog.showErrorDialog("Eroare la inițierea shutdown: ", ex);
            System.exit(1);
        }
    }

    private SystemShutdownExecutor() { }
}