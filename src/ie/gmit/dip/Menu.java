package ie.gmit.dip;

import java.io.File;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//This class handles all data and procedures processed through the menu.
public class Menu {

	private int key;
	private int offset;
	private String source;
	private Scanner s = new Scanner(System.in);
	private RailFenceCypher rfc;
	private FileHandler fh;
	private File f;

	// This method creates and runs the menu for the user.
	public void goMenu() {

		try {
			boolean keepRunning = true;

			while (keepRunning) {

				printMenu();

				int menuChoice = Integer.parseInt(s.next());

				switch (menuChoice) {
				case 1: // This case allows encryption or decryption of a file. I find it more intuitive
						// this way.
					enterFileSource();
					setKeyAndOffset();
					encryptOrDecrypt();
					break;
				case 2: // This case allows encryption only of a URL. A URL cannot be decrypted, only
						// the file created by encryption.
					enterURLSource();
					setKeyAndOffset();
					fh = new FileHandler(rfc);
					fh.parse(new URL(source).openStream(), true);
					break;
				case 3: // This case reads one line of a created rail fence and displays it on screen
						// for the user.
					if (rfc != null)
						fh.parseOneLine(new FileInputStream(f));
					else
						System.out.println(
								"No instance of a rail fence has been created.\nPlease process a file or URL to create a rail fence first!");
					break;
				case 4: // Ends the program
					quitMenu();
					keepRunning = false;
					break;

				}

			}
		} catch (NumberFormatException e) {
			System.out.println("\nYou must only enter an integer between 1 and 4!");
			goMenu();
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	// This method allows the user to decide whether to encrypt or decrypt the file
	// they entered.
	private void encryptOrDecrypt() throws FileNotFoundException, IOException {

		try {
			System.out.println("\n1 Encrypt " + source);
			System.out.println("2 Decrypt " + source);
			int choice2;
			do {
				choice2 = Integer.parseInt(s.next());
				if (choice2 == 1)
					createFileHandler(true);
				else if (choice2 == 2)
					createFileHandler(false);
				else
					System.out.println("\nPlease select either 1 for encrypt, or 2 for decrypt:");
			} while (choice2 != 1 && choice2 != 2);
		} catch (NumberFormatException e) {
			System.out.println("\nYou must only enter an integer of 1 or 2!");
			encryptOrDecrypt();
		}
	}

	// A simple method to read the URL source string.
	private void enterURLSource() {

		System.out.println("\nPlease enter a URL source for encryption:");
		source = s.next();
	}

	// A simple method to read the File name as a string. Then checks that the file
	// exists.
	private void enterFileSource() {

		getValidFile();
		if (f.isFile() == false) {
			while (f.isFile() == false) {
				System.out.println("\nThat is not a valid file!");
				getValidFile();
			}
		}
	}

	// Called by enterFileSource, this reads the source of the file.
	private void getValidFile() {

		System.out.println("\nPlease enter a valid file source for encryption/decryption:");
		source = s.next();
		f = new File(source);
	}

	// Method to read the key and offset which also handles incorrect user input.
	private void setKeyAndOffset() {

		try {
			System.out.println("\nPlease enter a key value for the number of rows:");
			key = Integer.parseInt(s.next());
			do {
				System.out.println("Please enter an offset value between 0 and " + (key - 1) + ":");
				offset = Integer.parseInt(s.next());
			} while (offset > key - 1);
			rfc = new RailFenceCypher(key, offset);
		} catch (NumberFormatException e) {
			System.out.println("\nYou must only enter an integer/number!");
			setKeyAndOffset();
		}
	}

	// This method links to the FileHandler class and begins the process of reading
	// the file line by line through parse.
	private void createFileHandler(boolean encrypt) throws FileNotFoundException, IOException {

		fh = new FileHandler(rfc);
		fh.parse(new FileInputStream(f), encrypt);

	}

	// Displays to the user that the program has been shut down.
	private void quitMenu() {

		System.out.println("\n###############################################################");
		System.out.println("Thank you for using the Rail Fence Cypher.");
		System.out.println("The application has been terminated.");
		System.out.println("###############################################################");

	}

	// Prints the menu.
	private void printMenu() {

		System.out.println("\n###############################################################");
		System.out.println("1. Process a File for Rail Fence Encryption/Decryption.");
		System.out.println("2. Process a URL for Rail Fence Encryption.");
		System.out.println("3. Display an Existing Rail Fence.");
		System.out.println("4. Exit the Application.");
		System.out.println("###############################################################");
		System.out.println("\nType Option [1 - 4]");
	}

}
