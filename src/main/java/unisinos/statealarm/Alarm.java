package unisinos.statealarm;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Alarm extends AlarmObserver {

    private AlarmState state;

    public Alarm() {
        this.state = new AlarmOff(this);
        awaitToTurnOff();
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
    
    private void awaitToTurnOff() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule (() -> {
            try (Scanner keyboard = new Scanner(System.in)) {
                keyboard.nextLine();
                new AlarmOff(this).setActive();
                System.exit(0);
            }
        }, 0, TimeUnit.SECONDS);
    }

}
