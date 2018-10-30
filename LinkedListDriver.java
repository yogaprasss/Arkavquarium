public class LinkedListDriver {
	public static void main(String[] args) {
		LinkedList<Integer> ayam = new LinkedList<Integer>();
		if (ayam.isEmpty()) {
			System.out.println("kosong");
		} else {
			System.out.println("isi");
		}
		for (int i = 0; i < 10; i++) {
			ayam.add(i);
		}
		/* if (!ayam.isEmpty()) {
			System.out.println("isi");
		} */
		System.out.println(ayam.find(10));
		System.out.println(ayam.find(3));
		System.out.println(ayam.length());
		System.out.println(ayam.get(5));
		ayam.remove(5);
		System.out.println(ayam.get(5));
	}
}
		