package unisinos.statealarm;

import java.time.Duration;
import unisinos.statealarm.constants.AlarmConstants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AlarmSet extends AlarmState implements AlarmConstants {

    private LocalDateTime setTime;
    private final int attempts;

    public AlarmSet(AlarmObserver observer, LocalDateTime setTime) {
        this(observer, setTime, MAX_RETRIES);
    }

    public AlarmSet(AlarmObserver observer, LocalDateTime setTime, int attempts) {
        super(observer);
        this.setTime = setTime;
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
        Duration duration = Duration.between(LocalDateTime.now(), setTime);
        observer.updateStateAfter(duration, () -> new AlarmRinging(observer, attempts));
    }

    @Override
    public String getUpdateMessage() {
        return "Alarme definido para " + setTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

}
