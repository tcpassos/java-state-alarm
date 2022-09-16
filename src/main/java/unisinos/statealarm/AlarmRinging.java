package unisinos.statealarm;

import java.time.LocalDateTime;

public class AlarmRinging extends AlarmState {

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
    public void update() {
        System.out.println("Beep Beep Beep!");
        LocalDateTime stopAlarmTime = LocalDateTime.now().plusSeconds(10);
        if (attempts > 0) {
            ScheduledAlarmState.schedule(new AlarmSet(observer, stopAlarmTime.plusMinutes(10), attempts - 1), stopAlarmTime);
        } else {
            ScheduledAlarmState.schedule(new AlarmOff(observer), stopAlarmTime);
        }
        observer.updateState(this);
    }

}
