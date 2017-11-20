
class Consumer extends Thread {
   private Buffer buffer;
   
   Consumer(Buffer b) { buffer = b; }
   public void run() {
	  int i = 0;
      while(true) {
         try {
			buffer.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
         i++;
      }    
   }
}   
