public class Siput implements Benda{
	private double cx;
	private double cy;
	private int hadap;
	private final int speed;
	private final double EPSILON = 0.00000001;
	
	public Siput () {
		speed = 33;
		cx = Utility.SCREEN_WIDTH/2;
		cy = 400;
		hadap = 1;
	}
	
	public void move (double sec_since_last, double now) {
		//move code goes here
		if (!Akuarium.ListKoin.isEmpty()) {
			/* System.out.println("lolos coeg"); */
			double y = (Akuarium.ListKoin.get(0).getY() - cy) * (Akuarium.ListKoin.get(0).getY() - cy);
			double x = (Akuarium.ListKoin.get(0).getX() - cx) * (Akuarium.ListKoin.get(0).getX() - cx);
			double min = Math.sqrt(y + x);
			int idx = 0;
			for(int i=1; i<=Akuarium.ListKoin.length(); i++) {
				y = (Akuarium.ListKoin.get(i).getY() - cy) * (Akuarium.ListKoin.get(i).getY() - cy);
				x = (Akuarium.ListKoin.get(i).getX() - cx) * (Akuarium.ListKoin.get(i).getX() - cx);
				double temp = Math.sqrt(y + x);
				if (temp < min) {
					min = temp;
					idx = i;
				}
			}
			
			if (Akuarium.ListKoin.get(idx).getX() > cx) {
				hadap = 1;
			} else {
				hadap = -1;
			}
			if (!(Math.abs(Akuarium.ListKoin.get(idx).getX() - cx) < EPSILON)) {
				cx += speed * hadap * sec_since_last;
			}
		}
	}

	//public void show () {
		//show code goes here
	//}

	public int getX () {
		return (int)cx;
	}

	public void setX (double x) {
		cx = x;
	}
	
	public int getY () {
		return (int)cy;
	}
	
	public int getHadap() {
		return hadap;
	}

	public int ambilUang () {
		//ambil uang code goes here
		int retval = 0;
		if (!Akuarium.ListKoin.isEmpty()) {
			int idx = -1;
			for (int i=0; i<=Akuarium.ListKoin.length(); i++) {
				double y = (Akuarium.ListKoin.get(i).getY() - cy) * (Akuarium.ListKoin.get(i).getY() - cy);
				double x = (Akuarium.ListKoin.get(i).getX() - cx) * (Akuarium.ListKoin.get(i).getX() - cx);
				double jarak = Math.sqrt(y + x);
				if (jarak < 50) {
					idx = i;		
					break;
				}
			}
			if (idx != -1) {
				retval = Akuarium.ListKoin.get(idx).getNilai();
				Akuarium.ListKoin.remove(Akuarium.ListKoin.get(idx));
			}
		}
		return retval;
	}
}
