package domain.message;

import java.time.LocalDateTime;

public class LogInfo extends Log{
	
	public LogInfo(LocalDateTime timestamp, String message) {
		super(timestamp, message);
	}

	@Override
	public String getLog() {
		return super.getTimestamp().getDayOfMonth() + " " + super.getTimestamp().getMonth().toString() + " " + super.getTimestamp().getYear() + " INFO: " + super.getMessage();
	}
	
}
