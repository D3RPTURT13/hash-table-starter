/*
 * This class sets up the has table data structure along with
 * the utility methods to manipulate it.
 */
public class HashTable {

	private Node[] map;
	private int tableSize;
	
	// constructor
	public HashTable(int tableSize) {
		map = new Node[tableSize];
		this.tableSize = tableSize;
	}
	
	/*
	 * This is the hash function.
	 */
	private int hash(String key) {
		int total = 0;
		if (!key.equals("")) {
			for (int i = 0; i < key.length(); i++) {
				total += key.codePointAt(i);
			}
		}
		return total % 8;
	}
	
	/*
	 * This method should insert a node containing the given key
	 * in the proper bucket in the hash table.  Insert new nodes
	 * at the head of each linked list for ease of implementation.
	 */
	public void insert(String key) {
		int index = hash(key);
		Node newNode = new Node(key);
		newNode.next = map[index];
		map[index] = newNode;
	}
	
	/*
	 * This method returs true if the search key is contained in the
	 * hash table, and false otherwise.
	 */
	public boolean search(String key) {
		int index = hash(key);
		boolean found = false;
		Node pointer = map[index];
		while (pointer != null) {
			if (key.equals(pointer.record)) {
				found = true;
				break;
			}
			pointer = pointer.next;
		}
		return found;
	}
	
	/*
	 * This method should print out the hash table row by row.
	 * Each line should print out a separate row of the table.
	 * Print the index followed a colon and then the records in that
	 * bucket with spaces between them.
	 * 
	 * For example:
	 * 1: Nicholas Mary Kim Jack
	 */
	public void printTable() {
		Node pointer;
		for (int i = 0; i < tableSize; i++ ) {
			pointer = map[i];
			System.out.print(i + ": ");
			while (pointer != null) {
				System.out.print(pointer.record + " ");
				pointer = pointer.next;
			}
			System.out.println();
		}
	}
	
	/*
	 * This method should delete ALL nodes matching the search key.
	 */
	public void delete(String key) {
		int index = hash(key);
		if (map[index] != null) {
			boolean headElement = key.equals(map[index].record);
			while (headElement) {
				map[index] = map[index].next;
				headElement = key.equals(map[index].record);
			}
		}
		Node pointer = map[index];
		Node back = pointer;
		while (pointer != null) {
			if (key.equals(pointer.record)) {
				back.next = pointer.next;
			} else {
				back = pointer;
			}
			pointer = pointer.next;
		}
	}
	
}
