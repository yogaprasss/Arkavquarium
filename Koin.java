public class Koin implements Benda{
	private int nilai;
	private double cx;
	private double cy;
	private int kecepatan;
	private final double EPSILON = 0.00000001;
	public Koin() {
		nilai = 0;
		cx = 0;
		cy = 0;
		kecepatan = 35;
	}
	
	public Koin(int n,double x,double y) {
		nilai = n;
		cx = x;
		cy = y;
		kecepatan = 35;
	}
	
	public void move (double sec_since_last, double now) {
		//move code goes here
		if (cy < Utility.SCREEN_HEIGHT-75) {	
			cy += kecepatan * sec_since_last;
		}
	}
	
	//public void show () {
		//show code goes here
	//}
	
	public int getNilai () {
		return nilai;
	}
	
	public double getX () {
		return cx;
	}
	
	public double getY () {
		return cy;
	}
	
	public void setNilai (int n) {
		nilai = n;
	}
	
	public void setX (double x) {
		cx = x;
	}
	
	public void setY (double y) {
		cy = y;
	}
	
	public void set(Object K) {
		if (K instanceof Koin) {
			Koin koin = (Koin)K;
			nilai = koin.nilai;
			kecepatan = koin.kecepatan;
			cx = koin.cx;
			cy = koin.cy;
		}
	}
	
	public boolean equals(Object a) {
		if (this == a) {
			return true;
		} else if (a == null) {
			return false;
		} else if (a instanceof Koin) {
			Koin k = (Koin)a;
			return (nilai==k.nilai) && Math.abs(cx - k.cx) < EPSILON && Math.abs(cy - k.cy) < EPSILON && (kecepatan == k.kecepatan);
		}
		return false;
	}
}
