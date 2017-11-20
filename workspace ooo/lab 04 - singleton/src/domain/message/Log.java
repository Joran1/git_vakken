package domain.message;

import java.time.LocalDateTime;

public abstract class Log {
	
	private LocalDateTime timestamp;
	private String message;
	
	public Log(LocalDateTime timestamp, String message) {
		this.timestamp = timestamp;
		this.message = message;		
	}

	public abstract String getLog();
	
	//GETTERS&SETTERS
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
}
