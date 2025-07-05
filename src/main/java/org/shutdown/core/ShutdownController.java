package org.shutdown.core;

import org.shutdown.ui.ShutdownDialog;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class ShutdownController {
    private static final int COUNTDOWN_SECONDS = 5 * 60;
    private volatile boolean shutdownTriggered = false;
    private Timer timer;
    private ShutdownDialog dialog;
    private int remainingSeconds;

    public void startShutdownCountdown() {
        remainingSeconds = COUNTDOWN_SECONDS;
        dialog = new ShutdownDialog(this);

        // Actualizare inițială a afișajului
        SwingUtilities.invokeLater(() -> {
            updateTimerDisplay();
            startTimer();         // Pornim timer-ul ÎNAINTE de afișarea dialogului
            dialog.showDialog();  // Afișăm dialogul (blochează execuția aici)
        });
    }

    private void startTimer() {
        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (shutdownTriggered) {
                    return;
                }

                if (remainingSeconds <= 0) {
                    triggerShutdown();
                    return;
                }

                remainingSeconds--;
                SwingUtilities.invokeLater(() -> updateTimerDisplay());
            }
        }, 1000, 1000);
    }

    private void updateTimerDisplay() {
        dialog.updateTimerDisplay(remainingSeconds);
    }

    public void onUserResponse(boolean wantsShutdown) {
        if (shutdownTriggered) {
            return; // Ignorăm dacă shutdown-ul a fost deja declanșat
        }

        if (wantsShutdown) {
            triggerShutdown();
        } else {
            cancelShutdown();
        }
    }

    private void triggerShutdown() {
        shutdownTriggered = true;
        cancelTimer();
        dialog.closeDialog();
        SystemShutdownExecutor.shutdown();
    }

    private void cancelShutdown() {
        shutdownTriggered = true;
        cancelTimer();
        dialog.closeDialog();
        System.exit(0);
    }

    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}