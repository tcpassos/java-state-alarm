package unisinos.statealarm;

import java.time.LocalDateTime;

public interface AlarmState {
    
    public AlarmState prepare(LocalDateTime time);
    
    public AlarmState disable();
    
}
