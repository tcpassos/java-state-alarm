package unisinos.statealarm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AlarmSet implements AlarmState {
    
    private static final int MAX_ATTEMPTS = 3;
    
    private LocalDateTime setTime;
    private int attempts;

    public AlarmSet(LocalDateTime setTime) {
        this(setTime, MAX_ATTEMPTS);
    }

    public AlarmSet(LocalDateTime setTime, int attempts) {
        this.setTime = setTime;
        this.attempts = attempts;
        System.out.println("Alarme definido para " + setTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")));
    }

    @Override
    public AlarmState prepare(LocalDateTime time) {
        return new AlarmSet(time);
    }

    @Override
    public AlarmState disable() {
        return new AlarmOff();
    }
    
}
