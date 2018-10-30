public abstract class Ikan {
	protected final int kecepatan;
	protected double start;
	protected double cx;
	protected double cy;
	protected int hadap;
	protected boolean hungry;
	protected double waktuGerak;
	protected double xDirect;
	protected double yDirect;
	
	public Ikan (int s) {
		kecepatan = s;
		waktuGerak = Utility.time_since_start();
		xDirect = -1 + 2 * Math.random(); 
		yDirect = -1 + 2 * Math.random();
		if (xDirect > 0) {
			hadap = 1;
		} else {
			hadap = -1;
		}
		start = Utility.time_since_start(); //harusnya time_since_start
		cx = 100 + Math.random() * (Utility.SCREEN_WIDTH - 150 - 100); //harus random
		cy = 100 + Math.random() * (Utility.SCREEN_HEIGHT - 200 - 100); //harus random
		hungry = false;
	}
	
	public abstract void death (double now);
	
	public abstract void makan ();
	
	public double getStart () {
		return start;
	}
	
	public double getX () {
		return cx;
	}
	
	public double getY () {
		return cy;
	} 
	
	public int getHadap() {
		return hadap;
	}
}