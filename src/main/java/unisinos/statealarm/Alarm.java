package unisinos.statealarm;

import java.time.LocalDateTime;

public class Alarm implements AlarmObserver {

    private AlarmState state;

    public Alarm() {
        this.state = new AlarmOff(this);
    }

    public void prepare(LocalDateTime time) {
        state = state.prepare(time);
    }

    public void disable() {
        state = state.disable();
    }

    @Override
    public void updateState(AlarmState newState) {
        state = newState;
    }

}
