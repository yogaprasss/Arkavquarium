public class Makanan implements Benda{
	private final int kecepatan;
	private double cx;
	private double cy;
	private final double EPSILON = 0.00000001;
	
	public Makanan () {
		kecepatan = 50;
		cx = 0;
		cy = 0;
	}
	
	public Makanan (double x, double y) {
		kecepatan = 75;
		cx = x;
		cy = y;
	}
	
	public void move (double sec_since_last,double now) {
		//move code goes here
		if (cy < Utility.SCREEN_HEIGHT) {
			cy += kecepatan * sec_since_last;
		} else {
			Akuarium.ListMakanan.remove(this);
		}
	}
	
	//public void show () {
		//show code goes here
	//}
	
	public double getX () {
		return cx;
	}
	
	public double getY () {
		return cy;
	}
	
	public void setX (double x) {
		cx = x;
	}
	
	public void setY (double y) {
		cy = y;
	}
	
	public boolean equals(Object m) {
		if (this == m) {
			return true;
		} else if (m == null) {
			return false;
		} else if (m instanceof Makanan) {
			Makanan food = (Makanan)m;
			return Math.abs(food.cx - cx) < EPSILON && Math.abs(food.cy - cy) < EPSILON;
		}
		return false;
	}
	
	public void set(Object M) {
		if (M instanceof Makanan) {
			Makanan makanan = (Makanan)M;
			cx = makanan.cx;
			cy = makanan.cy;
		}
	}
}
