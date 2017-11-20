public class SolutionWithNoSynchronization implements MemoryWrapper {

	private MemorySegment _memory = null;
	private int writers = 0;
	private int wantwrite = 0;
	private int readers = 0;

	public SolutionWithNoSynchronization() {
		_memory = new MemorySegment();
	} 

	public void read(Process p) {
		p.setState("wantread");
		synchronized (this) {
			while (wantwrite > 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			readers++;
		}
		p.setState("reading");
		_memory.read();
		synchronized (this) {
			readers--;
			notifyAll();
		}
		p.setState("idle");
	}

	public void write(Process p) {
		p.setState("wantwrite");
		synchronized (this) {
			wantwrite++;
			while (writers > 0 || readers > 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			writers++;
			p.setState("writing");
			_memory.write();
			writers--;
			wantwrite--;
			notifyAll();

		}
		p.setState("idle");
	}
}