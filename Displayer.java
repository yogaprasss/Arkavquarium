import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer; 
import javax.swing.SwingUtilities; 
 
public class Displayer extends JPanel implements KeyListener,ActionListener{
	private ImageIcon ikan;
	private ImageIcon piranha;
	private ImageIcon coin;
	private ImageIcon makanan;
	private ImageIcon background;
	private ImageIcon siput;
	private ImageIcon logo;
	private ImageIcon menu;
	
	public Displayer() {
        setFocusable(true);
		addKeyListener(this);
		
		this.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent me) { }
			public void mouseReleased(MouseEvent me) { }
			public void mouseEntered(MouseEvent me) { }
			public void mouseExited(MouseEvent me) { }
			public void mouseClicked(MouseEvent me) { 
				int mx = me.getX();
				int my = me.getY();
				if(me.getButton() == MouseEvent.BUTTON1) {
					if (!Akuarium.ListKoin.isEmpty()) {
						for (int i=0; i<=Akuarium.ListKoin.length(); i++) {
							int ex = (int)Akuarium.ListKoin.get(i).getX();
							int ye = (int)Akuarium.ListKoin.get(i).getY();
							if (Math.sqrt((mx-ex)*(mx-ex) + (my-ye)*(my-ye)) < 30) {
								Main.duit += Akuarium.ListKoin.get(i).getNilai();
								Akuarium.ListKoin.remove(Akuarium.ListKoin.get(i));
							}
						}
					} else if (Main.duit >= 5) {
						Akuarium.addMakanan(mx,my);
						Main.duit -= 5;
					}
				}
				if(me.getButton() == MouseEvent.BUTTON2) {
				//label.setText("Middle Click!");
				}
				if(me.getButton() == MouseEvent.BUTTON3) {
				//label.setText("Right Click!");
				}
			}
		});
	}
	
	public void paint (Graphics g) {
		if (Main.mainmenu) {
			background = new ImageIcon("background_blur.png");
			background.paintIcon(this,g,0,10);
			logo = new ImageIcon("rsz_1logo.png");
			logo.paintIcon(this,g,200,200);
			menu = new ImageIcon("mm.png");
			menu.paintIcon(this,g,30,50);
			g.drawString("PRESS Y TO PLAY",100,400);
			g.drawString("PRESS X TO QUIT",400,400);
		} else if (Main.cek == 2) {
			background = new ImageIcon("background_blur.png");
			background.paintIcon(this,g,0,10);
			logo = new ImageIcon("win.png");
			logo.paintIcon(this,g,40,200);
		} else if (Main.cek == 3) {
			background = new ImageIcon("background_blur.png");
			background.paintIcon(this,g,0,10);
			logo = new ImageIcon("lose.png");
			logo.paintIcon(this,g,40,200);
		} else {
			background = new ImageIcon("background.png");
			background.paintIcon(this,g,0,10);
			g.drawString("Uang = " + Integer.toString(Main.duit), 0,24);
			g.drawString("Telur dibeli = " + Integer.toString(3-Main.telur),550,24);
			g.drawString("Beli Guppy : Q | Beli Piranha : W | Beli Telur : R",188,25);
			
			for (int i = 0; i<=Akuarium.ListGuppy.length(); i++) {
				if (Akuarium.ListGuppy.get(i).getTahap() == 1) {
					if (Akuarium.ListGuppy.get(i).getHadap() == 1) {
						ikan = new ImageIcon("ikan_s_r.png");
						ikan.paintIcon(this,g,(int)Akuarium.ListGuppy.get(i).getX(),(int)Akuarium.ListGuppy.get(i).getY());
					} else {
						ikan = new ImageIcon("ikan_s_l.png");
						ikan.paintIcon(this,g,(int)Akuarium.ListGuppy.get(i).getX(),(int)Akuarium.ListGuppy.get(i).getY());
					}
				} else if (Akuarium.ListGuppy.get(i).getTahap() == 2) {
					if (Akuarium.ListGuppy.get(i).getHadap() == 1) {
						ikan = new ImageIcon("ikan_m_r.png");
						ikan.paintIcon(this,g,(int)Akuarium.ListGuppy.get(i).getX(),(int)Akuarium.ListGuppy.get(i).getY());
					} else {
						ikan = new ImageIcon("ikan_m_l.png");
						ikan.paintIcon(this,g,(int)Akuarium.ListGuppy.get(i).getX(),(int)Akuarium.ListGuppy.get(i).getY());
					}
				} else {
					if (Akuarium.ListGuppy.get(i).getHadap() == 1) {
						ikan = new ImageIcon("ikan_xl_r.png");
						ikan.paintIcon(this,g,(int)Akuarium.ListGuppy.get(i).getX(),(int)Akuarium.ListGuppy.get(i).getY());
					} else {
						ikan = new ImageIcon("ikan_xl_l.png");
						ikan.paintIcon(this,g,(int)Akuarium.ListGuppy.get(i).getX(),(int)Akuarium.ListGuppy.get(i).getY());
					}
				}
			}
			
			for (int i = 0; i<=Akuarium.ListKoin.length(); i++) {
				coin = new ImageIcon("rsz_coin.png");
				coin.paintIcon(this,g,(int)Akuarium.ListKoin.get(i).getX(),(int)Akuarium.ListKoin.get(i).getY());
			}
			
			if (Akuarium.snail.getHadap() == 1) {
				siput = new ImageIcon("snail_r.png");
				//System.out.println(Akuarium.snail.getX());
				siput.paintIcon(this,g,(int)Akuarium.snail.getX(),(int)Akuarium.snail.getY());
			} else {
				siput = new ImageIcon("snail_l.png");
				siput.paintIcon(this,g,(int)Akuarium.snail.getX(),(int)Akuarium.snail.getY());
			}
			
			for (int i = 0; i<=Akuarium.ListPiranha.length(); i++) {
				if (Akuarium.ListPiranha.get(i).getHadap() == 1) {
					ikan = new ImageIcon("piranha_r.png");
					ikan.paintIcon(this,g,(int)Akuarium.ListPiranha.get(i).getX(),(int)Akuarium.ListPiranha.get(i).getY());
				} else {
					ikan = new ImageIcon("piranha_l.png");
					ikan.paintIcon(this,g,(int)Akuarium.ListPiranha.get(i).getX(),(int)Akuarium.ListPiranha.get(i).getY());
				}
			}
			
			for (int i = 0; i<=Akuarium.ListMakanan.length(); i++) {
				ikan = new ImageIcon("food.png");
				ikan.paintIcon(this,g,(int)Akuarium.ListMakanan.get(i).getX(),(int)Akuarium.ListMakanan.get(i).getY());
			}
		}
		g.dispose();
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		repaint();
	}
	 
	@Override
	public void keyTyped (KeyEvent e) {	}
	 
	@Override
	public void keyPressed (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			//Beli Guppy
			if (Main.duit >= 50) {
				Akuarium.addGuppy();
				Main.duit -= 50;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			//Beli Piranha
			if (Main.duit >= 500) {
				Akuarium.addPiranha();
				Main.duit -= 500;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			//Beli Telur
			if (Main.duit >= 2000) {
				Main.telur--;
				Main.duit -= 2000;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_Y) {
			Main.mainmenu = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_X) {
			Main.mainmenu = false;
			Main.running = false;
			Main.cek = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			Main.duit += 5000;
		}
	}
	 
	@Override
	public void keyReleased (KeyEvent e) {}
	
}
