import dataStructures.HashTable;
import java.util.Scanner;

public class MyHashTable extends HashTable {
	static Scanner scan = new Scanner(System.in);

	public MyHashTable(int theDivisor) {
		super(theDivisor);
	}

	public Object updateElement(Object theKey, Object theElement) {
		Object elementToUpdate = get(theKey);
		put(theKey, theElement);
		return elementToUpdate;
	}

	public Object updateKey(Object theKey, Object theNewKey) {
		Object elementToUpdate = get(theKey);
		delete(theKey);
		put(theNewKey, elementToUpdate);
		return elementToUpdate;
	}

	public void delete(Object theKey) {
		int b = search(theKey);
		if (table[b] != null && table[b].key.equals(theKey)) {
			table[b] = null;
			size--;
		}
	}

	public static void main(String[] args) {
		System.out.println("How many elements do you need to use in hash table?");
		int n = scan.nextInt();
		MyHashTable myhash = new MyHashTable(n+1);
		for (int i = 0; i < n; i++) {
			System.out.print((i + 1) + ") Insert the key:");
			String theKey = scan.next();
			System.out.print((i + 1) + ") Insert the element:");
			String theElement = scan.next();
			myhash.put(theKey, theElement);
		}
		System.out.println("1) Update element\n 2) Update key\n 3) Delete element\n 4) Exit");
		System.out.println("Hash Table");
		myhash.output();
		int a = scan.nextInt();
		String theKey, theElement, theNewKey;
		switch (a) {
		case 1:
			System.out.print("Insert key:");
			theKey = scan.next();
			System.out.print("Insert element:");
			theElement = scan.next();
			System.out.println("Updated element:" + myhash.updateElement(theKey, theElement));
			myhash.output();
			break;
		case 2:
			System.out.print("Insert old key:");
			theKey = scan.next();
			System.out.print("Insert new key:");
			theNewKey = scan.next();
			System.out.println("Updated element:" + myhash.updateKey(theKey, theNewKey));
			myhash.output();
			break;
		case 3:
			System.out.print("Insert key to delete:");
			theKey = scan.next();
			myhash.delete(theKey);
			myhash.output();
			break;
		case 4:
            System.out.print("exit");
            return;
        default:
            System.out.println("Invalid choice. Please choose again.");

		}
	}
}