package ie.gmit.dip;

//This class handles all processes related to the actual cipher creation and reading.
public class RailFenceCypher {

	private char[][] cypherGrid = null;
	private char[] text = null;
	private int key;
	private int offset;

	// This constructor prevents an instance of a rail fence cipher from being
	// created without a key and offset being entered.
	public RailFenceCypher(int key, int offset) {

		this.key = key;
		this.offset = offset;
	}

	// The encrypt method processes the file line by line which is sent from the
	// Filehandler.parse method
	public String encrypt(String line) {

		gridBuilder(line);
		buildEncryptionRailFence(text);

		// Loop to build and return the encrypted string
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < key; row++) {
			for (int col = 0; col < text.length; col++) {
				if (cypherGrid[row][col] != '\u0000') {
					sb.append(cypherGrid[row][col]);
				}
			}
		}
		return sb.toString();
	}

	// The decrypt method processes the file line by line which is sent from the
	// Filehandler.parse method
	public String decrypt(String line) {

		gridBuilder(line);

		int row = offset;
		boolean down;
		if (row == (key - 1)) {
			down = false;
		} else {
			down = true;
		}
		// Inserts ¦ wherever there should be a character. Builds the zigzag.
		for (int col = 0; col < text.length; col++) {

			if (down) {
				cypherGrid[row][col] = '\u2028'; // This symbol is rare, so should rarely ever disturb the rail fence. Best I
											// could think of.
				row++;

			} else {
				cypherGrid[row][col] = '\u2028';
				row--;

			}

			if (row == 0 || row == key - 1) {
				down = !down;
			}
		}

		// Replaces the ¦ symbol with the correct character from the encrypted line.
		int index = 0;
		for (int row2 = 0; row2 < key; row2++) {
			for (int col2 = 0; col2 < text.length; col2++) {
				if (cypherGrid[row2][col2] == '\u2028') {
					cypherGrid[row2][col2] = text[index];
					index++;
				}
			}
		}

		// Builds the string by checking each column per row.
		StringBuilder sb = new StringBuilder();
		for (int col3 = 0; col3 < line.length(); col3++) {
			for (int row3 = 0; row3 < key; row3++) {
				if (cypherGrid[row3][col3] != '\u0000') {
					sb.append(cypherGrid[row3][col3]);
				}
			}
		}

		return sb.toString();
	}

	// The encrypt and showCypher methods both use this method to build the rail
	// fence.
	private void buildEncryptionRailFence(char[] line) {

		int row = offset;
		boolean down;
		if (row == (key - 1)) {
			down = false;
		} else {
			down = true;
		}
		// The loop enters each character from the line into the array as a zigzag
		// pattern. Direction is changed when top or bottom rows are reached.
		for (int col = 0; col < text.length; col++) {

			if (down) {
				cypherGrid[row][col] = line[col];
				row++;

			} else {
				cypherGrid[row][col] = line[col];
				row--;

			}

			if (row == 0 || row == key - 1) {
				down = !down;
			}
		}
	}

	// This method builds the 2d array based on the number of rows (key) and length
	// of the line received (line). It is common to all encrypt and decrypt
	// functions.
	private void gridBuilder(String line) {
		cypherGrid = new char[key][line.length()];
		text = line.toCharArray();

	}

	// This method is used to display a current rail fence cipher on screen.
	public void showCypher(String line) {

		gridBuilder(line);
		buildEncryptionRailFence(text);

		// Loop to display the grid of a rail fence cipher.
		for (int row = 0; row < key; row++) {
			for (int col = 0; col < text.length; col++) {
				System.out.print(cypherGrid[row][col]);
			}
			System.out.println();
		}
	}

}
