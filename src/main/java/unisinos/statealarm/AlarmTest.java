package unisinos.statealarm;

import java.time.LocalDateTime;
import java.time.Month;

public class AlarmTest {
    
    public static void main(String[] args) {
        Alarm alarm = new Alarm();
        alarm.prepare(LocalDateTime.of(2022, Month.SEPTEMBER, 15, 12, 0)); // 15/09/2022 12:00
    }
    
}
