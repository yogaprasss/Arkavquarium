public class Akuarium {
	public static LinkedList<Guppy> ListGuppy = new LinkedList<Guppy>();
	public static LinkedList<Piranha> ListPiranha = new LinkedList<Piranha>();
	public static LinkedList<Koin> ListKoin = new LinkedList<Koin>();
	public static LinkedList<Makanan> ListMakanan = new LinkedList<Makanan>();
	public static Siput snail = new Siput();
	
	public Akuarium() {
		addGuppy();
		addGuppy();
		addPiranha();
	}
	
	public static void addGuppy() {
		ListGuppy.add(new Guppy());
	}
	
	public static void addPiranha() {
		ListPiranha.add(new Piranha());
	}
	
	public static void addMakanan(double x,double y) {
		ListMakanan.add(new Makanan(x,y));
	}
	
}