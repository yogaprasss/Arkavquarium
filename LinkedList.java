public class LinkedList<E> {
	class Node<E> {
		public Node<E> next;
		public E data;
	}
	
	public Node<E> first;
	
	public LinkedList() {
		first = null;
	}
	
	public int find(E element) {
		if (isEmpty()) {
			return -1;
		} else {
			int index = 0;
			boolean found = false;
			Node<E> p = first;
			while (!found && p != null) {
				if (element.equals(p.data)) {
					found = true;
				} else {
					p = p.next;
					index++;
				}
			}
			if (found) {
				return index;
			} else {
				return -1;
			}
		}
	}
	
	public int length() {
		int idx = 0;
		Node<E> p = first;
		while (p != null) {
			p = p.next;
			idx++;
		}
		return idx-1;
	}
	
	public boolean isEmpty() {
		return (first == null);
	}
	
	public void add(E element) {
		if(isEmpty()) {
			// The list is empty
			first = new Node<E>();
			first.data = element;
			first.next = null;
		} else {
			Node<E> insdata = new Node<E>();
			insdata.data = element;
			insdata.next = null;
			Node<E> p = first;
			while(p.next != null) {
				p = p.next;
			}
			p.next = insdata;
		}
	}

	public void remove(E element) {
		int index = find(element);
		if (index != -1) {
			if (index == 0) {
				Node<E> p = first; 
				first = first.next;
				p.next = null;
			} else {
				Node<E> p = first;
				for (int i = 0; i<index-1; i++) {
					p = p.next;
				}
				Node<E> q = p.next;
				p.next = q.next;
				q.next = null;
			}
		}
	}	


	public E get(int index) {
		if(index == 0) {
			// Get the first element
			return first.data;
		} else {
			// Get the index'th element
			Node<E> curr = first;
			for(int i = 0; i < index; ++i) {
				curr = curr.next;
			}
			return curr.data;
		}
	}
}