package unisinos.statealarm;

import java.time.Duration;
import unisinos.statealarm.constants.AlarmConstants;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlarmRinging extends AlarmState implements AlarmConstants {

    private final int attempts;

    public AlarmRinging(AlarmObserver observer, int attempts) {
        super(observer);
        this.attempts = attempts;
    }

    @Override
    public AlarmState prepare(LocalDateTime time) {
        return new AlarmSet(observer, time);
    }

    @Override
    public AlarmState disable() {
        return new AlarmOff(observer);
    }

    @Override
    public void setActive() {
        super.setActive();
        if (attempts > 0) {
            // Sleep mode
            LocalDateTime alarmTimeAfterSleepMode = LocalDateTime.now().plusMinutes(SLEEP_MODE_MINUTES)
                                                                       .plusSeconds(RINGING_TIME_SECONDS);
            observer.updateStateAfter(Duration.ofSeconds(RINGING_TIME_SECONDS),
                                      () -> new AlarmSet(observer, alarmTimeAfterSleepMode, attempts - 1));
        } else {
            // Turn off the alarm
            observer.updateStateAfter(Duration.ofSeconds(RINGING_TIME_SECONDS), () -> new AlarmOff(observer));
        }
//        try {
//            awaitToTurnOff(RINGING_TIME_SECONDS);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(AlarmRinging.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public String getUpdateMessage() {
        return "Beep Beep Beep!\nPressione [Enter] para desativar o alarme.";
    }

    private void awaitToTurnOff(int timeoutInSeconds) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule (() -> {
            try (Scanner keyboard = new Scanner(System.in)) {
                keyboard.nextLine();
                observer.cancelScheduledState();
                new AlarmOff(observer).setActive();
            }
        }, timeoutInSeconds, TimeUnit.SECONDS);
    }

}
