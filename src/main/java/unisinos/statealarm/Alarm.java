package unisinos.statealarm;

import java.time.LocalDateTime;

public class Alarm {
    
    private AlarmState state;

    public Alarm() {
        this.state = new AlarmOff();
    }
    
    public void prepare(LocalDateTime time) {
        state = state.prepare(time);
    }
    
    public void disable() {
        state = state.disable();
    }
    
}
