package domain;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import domain.message.Log;

public class LogWriter{
	
	private static LogWriter instance = new LogWriter();

	private LogWriter(){
	}
	
	public static LogWriter getInstance(){
		return instance;
	}
	
	public void writeLog(Log message) throws IOException {
		String fileName = "test.txt";
		
		FileWriter writer = null;
		writer = new FileWriter(fileName,true); 
		writer.append(message.getLog()+"\r\n");
		writer.close();
	}

}
