package unisinos.statealarm;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduledAlarmState {

    public static void schedule(AlarmState state, Temporal scheduledTime) {
        ScheduledAlarmState scheduledAlarmState = new ScheduledAlarmState(state, scheduledTime);
        scheduledAlarmState.scheduleTask();
    }

    private final Timer timer;
    private final AlarmState state;
    private final Temporal scheduledTime;

    private ScheduledAlarmState(AlarmState state, Temporal scheduledTime) {
        this.timer = new Timer();
        this.state = state;
        this.scheduledTime = scheduledTime;
    }

    private void scheduleTask() {
        long delay = Duration.between(LocalDateTime.now(), scheduledTime).toMillis();
        timer.schedule(new TimedAlarmTask(), delay);
    }

    private class TimedAlarmTask extends TimerTask {
        @Override
        public void run() {
            state.update();
            timer.cancel();
        }
    }

}
