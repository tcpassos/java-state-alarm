package unisinos.statealarm;

import java.time.LocalDateTime;

public abstract class AlarmState {

    protected AlarmObserver observer;

    public AlarmState(AlarmObserver observer) {
        this.observer = observer;
    }

    public abstract AlarmState prepare(LocalDateTime time);

    public abstract AlarmState disable();
    
    public abstract String getUpdateMessage();

    public void setActive() {
        observer.updateState(this);
    }

}
