
class Producer extends Thread {
   private Buffer buffer;
     
   Producer(Buffer b) { buffer = b; }
   public void run() {
	 int i = 0;
     while(true) {
    	 
        try {
			buffer.put((char)('A'+ i%26 ));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} i++; } 
   }
}    

