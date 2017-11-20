package ui;

import java.io.IOException;
import java.time.LocalDateTime;

import domain.LogWriter;
import domain.message.*;

public class Ui {

	public static void main(String[] args) throws IOException {

		LocalDateTime l = LocalDateTime.now();		
		Throwable t = new Throwable();
		
		Log i = new LogInfo(l, "information is given");
		Log w = new LogWarning(l, "this is a warning");
		Log e = new LogException(l, "error 404", t);
		
		LogWriter lw = LogWriter.getInstance();	
		lw.writeLog(i);		
		lw.writeLog(w);
		lw.writeLog(e);	
		
	}

}
