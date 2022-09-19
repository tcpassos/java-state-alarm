package unisinos.statealarm;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public abstract class AlarmObserver {
    
    private ScheduledExecutorService executor;
    ScheduledFuture<?> scheduledFuture;

    public abstract void updateState(AlarmState newState);
    
    public void updateStateAfter(Duration delay, Supplier<AlarmState> newState) {
        executor = Executors.newSingleThreadScheduledExecutor();
        scheduledFuture = executor.schedule(() -> {
                newState.get().setActive();
            },
            delay.toMillis(),
            TimeUnit.MILLISECONDS);
    }
    
    public void cancelScheduledState() {
        if (Objects.nonNull(scheduledFuture)) {
            scheduledFuture.cancel(true);
        }
    }

}
