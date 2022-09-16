package unisinos.statealarm;

import java.time.LocalDateTime;

public class AlarmOff implements AlarmState {

    @Override
    public AlarmState prepare(LocalDateTime time) {
        return new AlarmSet(time);
    }

    @Override
    public AlarmState disable() {
        return this;
    }
    
}
