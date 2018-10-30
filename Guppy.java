public class Guppy extends Ikan implements Benda{
	private int countMakanan;
	private int tahap;
	private double startKoin;
	private final double EPSILON = 0.000000001;
	
	public Guppy () {
		super(75);
		startKoin = Utility.time_since_start(); //harusnya time_since_start
		tahap = 1;
		countMakanan = 0;
	}
	
	public void move (double sec_since_last, double now) {
		//move code goes here
		if (!hungry || Akuarium.ListMakanan.isEmpty()) {
			if (now - waktuGerak > 2) {
				double xrand = Math.random() * 180;
				double yrand = Math.random() * 180;
		
				if (cx <= 0) {
					xrand = (Math.random() * 180) - 90;
				} else if (cx >= Utility.SCREEN_WIDTH-100) {
					xrand = (Math.random() * 180) + 90;
				} else {
					xrand = Math.random() * 360;
				}

				if (cy <= 50) {
					yrand = (Math.random() * 180);
				} else if (cy >= Utility.SCREEN_HEIGHT-100) {
					yrand = (Math.random() * 180) + 180;
				} else {
					yrand = Math.random() * 360;
				}

				if (Math.cos(Math.toRadians(xrand)) > 0) {
					hadap = 1;
				} else {
					hadap = -1;
				}
		
				cx += kecepatan * Math.cos(Math.toRadians(xrand)) * sec_since_last;
				cy += kecepatan * Math.sin(Math.toRadians(yrand)) * sec_since_last;
				waktuGerak = Utility.time_since_start();
				xDirect = Math.cos(Math.toRadians(xrand));
				yDirect = Math.sin(Math.toRadians(yrand));
			} else {
				if (cx <= 0) {
					double xrand = (Math.random() * 180) - 90;
					xDirect = Math.cos(Math.toRadians(xrand));
				} else if (cx >= Utility.SCREEN_WIDTH - 100) {
					double xrand = (Math.random() * 180) + 90;
					xDirect = Math.cos(Math.toRadians(xrand));
				}

				if (cy <= 50) {
					double yrand = (Math.random() * 180);
					yDirect = Math.sin(Math.toRadians(yrand));
				} else if (cy >= Utility.SCREEN_HEIGHT - 100) {
					double yrand = (Math.random() * 180) + 180;
					yDirect = Math.sin(Math.toRadians(yrand));
				} 
				
				if (xDirect > 0) {
					hadap = 1;
				} else {
					hadap = -1;
				}
				cx += kecepatan * xDirect * sec_since_last;
				cy += kecepatan * yDirect * sec_since_last;
			}
				
		} else {
			double y = (Akuarium.ListMakanan.get(0).getY() - cy) * (Akuarium.ListMakanan.get(0).getY() - cy);
			double x = (Akuarium.ListMakanan.get(0).getX() - cx) * (Akuarium.ListMakanan.get(0).getX() - cx);
			double min = Math.sqrt(y + x);
			int idx = 0;
			for(int i=1; i<=Akuarium.ListMakanan.length(); i++) {
				y = (Akuarium.ListMakanan.get(i).getY() - cy) * (Akuarium.ListMakanan.get(i).getY() - cy);
				x = (Akuarium.ListMakanan.get(i).getX() - cx) * (Akuarium.ListMakanan.get(i).getX() - cx);
				double temp = Math.sqrt(y + x);
				if (temp < min) {
					min = temp;
					idx = i;
				}
			}
			double arah = Math.atan2(Akuarium.ListMakanan.get(idx).getY()-cy, Akuarium.ListMakanan.get(idx).getX()-cx);
			if (Math.cos(arah) > 0) {
				hadap = 1;
			} else {
				hadap = -1;
			}
			cx += kecepatan * Math.cos(arah) * sec_since_last;
			cy += kecepatan * Math.sin(arah) * sec_since_last;
		}
	}
	
	
	public void death (double now) {
		//death code goes here
		if (now - start > 15) {
			Akuarium.ListGuppy.remove(this);
		}
	}
	
	/* public void show () {
		//show code goes here
	} */
	
	public void makan () {
		//makan code goes here
		if (!Akuarium.ListMakanan.isEmpty() && hungry) {
			int idx = -1;
			for (int i=0; i<=Akuarium.ListMakanan.length(); i++) {
				double y = (Akuarium.ListMakanan.get(i).getY() - cy) * (Akuarium.ListMakanan.get(i).getY() - cy);
				double x = (Akuarium.ListMakanan.get(i).getX() - cx) * (Akuarium.ListMakanan.get(i).getX() - cx);
				double jarak = Math.sqrt(y + x);
				if (jarak < 10) {
					idx = i;		
					break;
				}
			}
			if (idx != -1) {
				Akuarium.ListMakanan.remove(Akuarium.ListMakanan.get(idx));
				start = Utility.time_since_start();
				hungry = false;
				countMakanan++;
			}	
		}
	}
	
	public void tumbuh () {
		if (countMakanan == 10) {
			tahap = 2;
		} else
		if (countMakanan == 30) {
			tahap = 3;
		}
	}
	
	public void cash (double now) {
		if (now - startKoin > 8) {
			Koin uang = new Koin();
			if (tahap == 1) {
				uang.setNilai(10);
				uang.setX(cx);
				uang.setY(cy);
			} else 
			if (tahap == 2) {
				uang.setNilai(15);
				uang.setX(cx);
				uang.setY(cy);
			} else {
				uang.setNilai(35);
				uang.setX(cx);
				uang.setY(cy);
			}
			//visualization code goes here
			Akuarium.ListKoin.add(uang);
			startKoin = Utility.time_since_start();
		}
	}
	
	public int getTahap () {
		return tahap;
	}
	
	public void updateHunger (double now) {
		if ((now - start) >= 10) {
			hungry = true;
		}
	}
	
	public boolean equals(Object ikan) {
		if (this == ikan) {
			return true;
		} else if (ikan == null) {
			return false;
		} else if (ikan instanceof Guppy) {
			Guppy g = (Guppy)ikan;
			return (Math.abs(start - g.start) < EPSILON && Math.abs(startKoin - g.startKoin) < EPSILON && Math.abs(cx - g.cx) < EPSILON && Math.abs(cy - g.cy) < EPSILON && hadap == g.hadap && hungry == g.hungry && countMakanan == g.countMakanan && tahap == g.tahap);
		}
		return false;
	}
	
	public void set(Object G) {
		if (G instanceof Guppy) {
			Guppy guppy = (Guppy)G;
			start = guppy.start;
			startKoin = guppy.startKoin;
			cx = guppy.cx;
			cy = guppy.cy;
			tahap = guppy.tahap;
			hadap = guppy.hadap;
			countMakanan = guppy.countMakanan;
			hungry = guppy.hungry;
		}
	}
}
