package main;

public class HashIndexTable {

	private final byte[] hashTable = new byte[32]; // must be a power of two

	public HashIndexTable() {
	}

	public HashIndexTable(HashIndexTable original) {
		System.arraycopy(original.hashTable, 0, hashTable, 0, hashTable.length);
	}

	void add(String name, int index) {
		int slot = hashSlotFor(name);
		if (index < 0xff) {
			// increment by 1, 0 stands for empty
			hashTable[slot] = (byte) (index + 1);
		} else {
			hashTable[slot] = 0;
		}
	}

	void remove(int index) {
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] == index + 1) {
				hashTable[i] = 0;
			} else if (hashTable[i] > index + 1) {
				hashTable[i]--;
			}
		}
	}

	int get(Object name) {
		int slot = hashSlotFor(name);
		// subtract 1, 0 stands for empty
		return (hashTable[slot] & 0xff) - 1;
	}

	private int hashSlotFor(Object element) {
		return element.hashCode() & hashTable.length - 1;
	}

}