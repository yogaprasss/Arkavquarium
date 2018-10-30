public class Piranha extends Ikan implements Benda {
	private final double EPSILON = 0.00000001;
	
	public Piranha () {
		super(130);
	}
	
	public void move (double sec_since_last, double now) {
		//move code goes here
		if (!hungry || Akuarium.ListGuppy.isEmpty()) {
			if (now - waktuGerak > 1) {
				double xrand = Math.random() * 180;
				double yrand = Math.random() * 180;
	
				if (cx <= 50) {
					xrand = (Math.random() * 180) - 90;
				} else if (cx >= Utility.SCREEN_WIDTH - 100) {
					xrand = (Math.random() * 180) + 90;
				} else {
					xrand = Math.random() * 360;
				}

				if (cy <= 50) {
					yrand = (Math.random() * 180);
				} else if (cy >= Utility.SCREEN_HEIGHT - 100) {
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
				if (cx <= 50) {
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
			
			//System.out.println(cx);
			//System.out.println(cy);
		} else {
			double y = (Akuarium.ListGuppy.get(0).getY() - cy) * (Akuarium.ListGuppy.get(0).getY() - cy);
			double x = (Akuarium.ListGuppy.get(0).getX() - cx) * (Akuarium.ListGuppy.get(0).getX() - cx);
			double min = Math.sqrt(y + x);
			int idx = 0;
			for(int i=1; i<=Akuarium.ListGuppy.length(); i++) {
				y = (Akuarium.ListGuppy.get(i).getY() - cy) * (Akuarium.ListGuppy.get(i).getY() - cy);
				x = (Akuarium.ListGuppy.get(i).getX() - cx) * (Akuarium.ListGuppy.get(i).getX() - cx);
				double temp = Math.sqrt(y + x);
				if (temp < min) {
					min = temp;
					idx = i;
				}
			}
			double arah = Math.atan2(Akuarium.ListGuppy.get(idx).getY()-cy, Akuarium.ListGuppy.get(idx).getX()-cx);
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
			Akuarium.ListPiranha.remove(this);
		}
	}
	
	//public void show () {
		//show code goes here
	//}
	
	public void makan () {
		//makan code goes here
		if (!Akuarium.ListGuppy.isEmpty() && hungry) {
			int idx = -1;
			for (int i=0; i<=Akuarium.ListGuppy.length(); i++) {
				double y = (Akuarium.ListGuppy.get(i).getY() - cy) * (Akuarium.ListGuppy.get(i).getY() - cy);
				double x = (Akuarium.ListGuppy.get(i).getX() - cx) * (Akuarium.ListGuppy.get(i).getX() - cx);
				double jarak = Math.sqrt(y + x);
				if (jarak < 10) {
					idx = i;		
					break;
				}
			}
			if (idx != -1) {
				cash(Akuarium.ListGuppy.get(idx).getTahap());
				Akuarium.ListGuppy.remove(Akuarium.ListGuppy.get(idx));
				start = Utility.time_since_start();
				hungry = false;
			}
		}
	}
	
	public void cash (int tahap) {
		Akuarium.ListKoin.add(new Koin(100 * (tahap + 1), cx, cy));
		//visualization code goes here
	}
	
	public void updateHunger (double now) {
		if ((now - start) >= 10) {
			hungry = true;
		}
	}
	
	public boolean equals(Object p) {
		if (this == p) {
			return true;
		} else if (p == null) {
			return false;
		} else if (p instanceof Piranha) {
			Piranha piranha = (Piranha)p;
			return Math.abs(start - piranha.start) < EPSILON && Math.abs(cx - piranha.cx) < EPSILON && Math.abs(cy - piranha.cy) < EPSILON && hadap == piranha.hadap && hungry == piranha.hungry;
		}
		return false;
	}
	
	public void set(Object P) {
		if (P instanceof Piranha) {
			Piranha piranha = (Piranha)P;
			start = piranha.start;
			cx = piranha.cx;
			cy = piranha.cy;
			hadap = piranha.hadap;
			hungry = piranha.hungry;
		}
	}
}
