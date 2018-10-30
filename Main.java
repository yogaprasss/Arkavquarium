import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	public static int duit = 200;
	public static int telur = 3;
	public static boolean mainmenu = true;
	public static boolean running = true;
	public static int cek = 0;
	public static void main (String[] args) {
		double prev_time = Utility.time_since_start();
		JFrame f = new JFrame("ArkavQuarium");
		while (mainmenu) {
			Displayer e = new Displayer();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize(Utility.SCREEN_WIDTH,Utility.SCREEN_HEIGHT);
			f.setVisible(true);
			f.add(e);
		}
		Akuarium oopquarium = new Akuarium();
		while (running) {
			int tambahUang = 0;
			double now = Utility.time_since_start();
			double sec_since_last = now - prev_time;
			prev_time = now;
			
			//Mengatur Guppy
			if (!oopquarium.ListGuppy.isEmpty()) {
				for (int i=0; i<=oopquarium.ListGuppy.length(); i++) {
					oopquarium.ListGuppy.get(i).updateHunger(now);
					oopquarium.ListGuppy.get(i).move(sec_since_last,now);
					oopquarium.ListGuppy.get(i).makan();
					oopquarium.ListGuppy.get(i).tumbuh();
					oopquarium.ListGuppy.get(i).cash(now);
					oopquarium.ListGuppy.get(i).death(now);
				}
			}

			//Mengatur Piranha
			if (!oopquarium.ListPiranha.isEmpty()) {
				for (int i=0; i<=oopquarium.ListPiranha.length(); i++) {
					oopquarium.ListPiranha.get(i).updateHunger(now);
					oopquarium.ListPiranha.get(i).move(sec_since_last,now);
					oopquarium.ListPiranha.get(i).makan();
					oopquarium.ListPiranha.get(i).death(now);
				}
			}
		
			//Mengatur Koin
			if (!oopquarium.ListKoin.isEmpty()) {
				//System.out.println("isi");
				for (int i=0; i<=oopquarium.ListKoin.length(); i++) {
					oopquarium.ListKoin.get(i).move(sec_since_last,now);
				}
			}

			//Mengatur Makanan
			if (!oopquarium.ListMakanan.isEmpty()) {
				for (int i=0; i<=oopquarium.ListMakanan.length(); i++) {
					oopquarium.ListMakanan.get(i).move(sec_since_last,now);
				}
			}

			//Mengatur Siput
			oopquarium.snail.move(sec_since_last,now);
			//System.out.println(oopquarium.snail.getX());
			tambahUang = oopquarium.snail.ambilUang();
			duit += tambahUang;
		
			//Kondisi kalah
			if (oopquarium.ListGuppy.isEmpty() && oopquarium.ListPiranha.isEmpty() && duit < 100) {
				running = false;
			}
		
			Displayer e = new Displayer();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize(Utility.SCREEN_WIDTH,Utility.SCREEN_HEIGHT);
			f.setVisible(true);
			f.add(e);
			if (telur == 0) {
				running = false;
			}
		}
		if (cek == 0) {
			double now = Utility.time_since_start();
			if (telur == 0) {
				cek = 2;
				double now2 = Utility.time_since_start();
				while (now2-now < 3) {
					now2 = Utility.time_since_start();
					Displayer e = new Displayer();
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setSize(Utility.SCREEN_WIDTH,Utility.SCREEN_HEIGHT);
					f.setVisible(true);
					f.add(e);
				}
			} else {
				cek = 3;
				double now2 = Utility.time_since_start();
				while (now2-now < 3) {
					now2 = Utility.time_since_start();
					Displayer e = new Displayer();
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setSize(Utility.SCREEN_WIDTH,Utility.SCREEN_HEIGHT);
					f.setVisible(true);
					f.add(e);
				}
			}
		}
		f.dispose();
	}
}