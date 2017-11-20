package domain.message;

import java.time.LocalDateTime;

public class LogException extends Log{
	
	private Throwable exception;
	
	public LogException(LocalDateTime timestamp, String message, Throwable exception) {
		super(timestamp, message);
		this.exception = exception;
	}

	@Override
	public String getLog() {
		return super.getTimestamp().getDayOfMonth() + " " + super.getTimestamp().getMonth().toString() + " " + super.getTimestamp().getYear() + " EXCEPTION: " + super.getMessage() + " " + this.exception.getStackTrace().toString();
	}
	
}