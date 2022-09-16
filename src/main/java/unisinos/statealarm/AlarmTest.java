package unisinos.statealarm;

import java.time.LocalDateTime;
import java.time.Month;

public class AlarmTest {

    public static void main(String[] args) {
        Alarm alarm = new Alarm();
        alarm.prepare(LocalDateTime.of(2022, Month.SEPTEMBER, 16, 17, 38));
    }

}
