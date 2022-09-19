package unisinos.statealarm;

import java.time.Duration;
import unisinos.statealarm.constants.AlarmConstants;
import java.time.LocalDateTime;

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
    }

    @Override
    public String getUpdateMessage() {
        return "Beep Beep Beep!\nPressione [Enter] para desativar o alarme.";
    }

}
