package unisinos.statealarm;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

public abstract class AlarmObserver {

    private Timer timer;

    public abstract void updateState(AlarmState newState);
    
    public void updateStateAfter(Duration delay, Supplier<AlarmState> newState) {
        timer = new Timer();
        timer.schedule(new TimedAlarmTask(newState), delay.toMillis());
    }
    
    public void cancelScheduledState() {
        timer.cancel();
    }

    private class TimedAlarmTask extends TimerTask {
        Supplier<AlarmState> newState;
        public TimedAlarmTask(Supplier<AlarmState> newState) {
            this.newState = newState;
        }
        @Override
        public void run() {
            newState.get().setActive();
            timer.cancel();
        }
    }

}
