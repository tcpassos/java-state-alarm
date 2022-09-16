package unisinos.statealarm;

import java.time.LocalDateTime;

public class AlarmOff extends AlarmState {

    public AlarmOff(AlarmObserver observer) {
        super(observer);
    }

    @Override
    public AlarmState prepare(LocalDateTime time) {
        AlarmSet newState = new AlarmSet(observer, time);
        newState.printSetTime();
        return newState;
    }

    @Override
    public AlarmState disable() {
        return this;
    }

    @Override
    public void update() {
        System.out.println("Alarme desarmado");
        super.update();
    }

}
