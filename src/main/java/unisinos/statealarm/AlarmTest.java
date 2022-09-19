package unisinos.statealarm;

import java.time.LocalDateTime;

public class AlarmTest {

    public static void main(String[] args) throws InterruptedException {
        Alarm alarm = new Alarm();
        LocalDateTime wakeUpTime = LocalDateTime.now().plusSeconds(5);
        alarm.prepare(wakeUpTime);
    }

}
