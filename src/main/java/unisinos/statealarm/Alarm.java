package unisinos.statealarm;

import java.time.LocalDateTime;

public class Alarm extends AlarmObserver {

    private AlarmState state;

    public Alarm() {
        this.state = new AlarmOff(this);
    }

    public void prepare(LocalDateTime time) {
        state.prepare(time).setActive();
    }

    public void disable() {
        state.disable().setActive();
    }

    @Override
    public void updateState(AlarmState newState) {
        state = newState;
        System.out.println(state.getUpdateMessage());
    }

}
