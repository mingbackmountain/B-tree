import java.io.*;

class Tree234App {
	public static void main(String[] args) throws IOException {
		long value;
		Tree23 theTree = new Tree23();

		while (true) {
			System.out.print("Enter first letter of ");
			System.out.print("show, insert, or find: ");
			char choice = getChar();
			switch (choice) // check the character that we in put
			{
			case 's': // character is "s"
				theTree.displayTree(); // sent the value in class Tree234
				break; // stop when the program print out
			case 'i':
				System.out.print("Enter value to insert: ");
				value = getInt();
				theTree.insert(value);
				break;
			case 'f':
				System.out.print("Enter value to find: ");
				value = getInt();
				int found = theTree.find(value);
				if (found != -1) {
					System.out.print("Found " + value + " at ");
					theTree.findDisplayTree(value);
					System.out.print("\n");
				} else
					System.out.println("Could not find " + value);
				break;
			default: // if character that we type is doesn't equal
				System.out.print("Invalid entry\n");
			}
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}