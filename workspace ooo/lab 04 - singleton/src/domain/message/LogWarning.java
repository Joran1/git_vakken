package domain.message;

import java.time.LocalDateTime;

public class LogWarning extends Log{
	
	public LogWarning(LocalDateTime timestamp, String message) {
		super(timestamp, message);
	}

	@Override
	public String getLog() {
		return super.getTimestamp().getDayOfMonth() + " " + super.getTimestamp().getMonth().toString() + " " + super.getTimestamp().getYear() + " WARNING: " + super.getMessage();
	}
	
}