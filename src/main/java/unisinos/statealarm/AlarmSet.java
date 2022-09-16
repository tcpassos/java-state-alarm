package unisinos.statealarm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AlarmSet extends AlarmState {

    private static final int MAX_ATTEMPTS = 3;

    private LocalDateTime setTime;

    public AlarmSet(AlarmObserver observer, LocalDateTime setTime) {
        this(observer, setTime, MAX_ATTEMPTS);
        this.setTime = setTime;
    }

    public AlarmSet(AlarmObserver observer, LocalDateTime setTime, int attempts) {
        super(observer);
        ScheduledAlarmState.schedule(new AlarmRinging(observer, attempts), setTime);
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
        printSetTime();
        super.update();
    }

    public void printSetTime() {
        System.out.println("Alarme definido para " + setTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }

}
