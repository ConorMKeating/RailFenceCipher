package ie.gmit.dip;

import java.io.*;

//The FileHandler class primarily deals with all file related procedures.
public class FileHandler {

	private RailFenceCypher cypher;

	public FileHandler(RailFenceCypher c) throws FileNotFoundException, IOException {

		cypher = c;
	}

	// The parse method takes in a file to read and encrypts or decrypts based on
	// the user choice.
	public void parse(InputStream in, boolean encrypt) {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			FileWriter fw;
			String line;
			if (encrypt) {
				fw = new FileWriter(new File("EncryptedText.txt"));
			} else {
				fw = new FileWriter(new File("DecryptedText.txt"));
			}
			// Loop to process each line of the file or URL
			while ((line = br.readLine()) != null) {

				String text = encrypt ? cypher.encrypt(line) : cypher.decrypt(line);

				fw.write(text + "\n");
			}
			if (encrypt)
				System.out.println("\nFile *EncryptedText.txt* successfully created!");
			else
				System.out.println("\nFile *DecryptedText.txt* successfully created!");
			br.close();
			fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// This method is called from the Display Rail Fence menu option. It finds a
	// line greater than a certain length in case the first few lines of a file or
	// URL are very short.
	public void parseOneLine(InputStream in) {

		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;

		try {
			while ((line = br.readLine()) != null) {
				line = br.readLine();
				if (line.length() >= 10)
					break;
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		displayRailFence(line);

	}

	// This method is called by parseOneLine and in turn, calls showCypher which is
	// in the RailFenceCypher class.
	public void displayRailFence(String line) {

		cypher.showCypher(line);

	}

}
