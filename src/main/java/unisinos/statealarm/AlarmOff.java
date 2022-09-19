package unisinos.statealarm;

import java.time.LocalDateTime;

public class AlarmOff extends AlarmState {

    public AlarmOff(AlarmObserver observer) {
        super(observer);
    }

    @Override
    public AlarmState prepare(LocalDateTime time) {
        return new AlarmSet(observer, time);
    }

    @Override
    public AlarmState disable() {
        return this;
    }

    @Override
    public String getUpdateMessage() {
        return "Alarme desarmado";
    }

}
