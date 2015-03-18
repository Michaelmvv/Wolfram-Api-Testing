package apiKey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class KeyFile {

	String API_KEY;
	String API_KEY_STORAGE_PATH = "WolframApi/";
	String API_KEY_STORAGE_FILE_NAME = "api.txt";
	String API_KEY_STORAGE_FILE = API_KEY_STORAGE_PATH
			+ API_KEY_STORAGE_FILE_NAME;
	String EMPTY_FILE_CONTENT = "";

	File path = new File(API_KEY_STORAGE_PATH);
	File file = new File(API_KEY_STORAGE_FILE);

	public void saveAPIKey() throws FileNotFoundException {
		check();
		try {
			PrintWriter writer = new PrintWriter(new File(API_KEY_STORAGE_FILE));
			API_KEY = JOptionPane.showInputDialog("Paste API key here");
			writer.write(API_KEY);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getApiKey() throws IOException {
		check();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String key = reader.readLine();
		reader.close();

		if (key.equals(EMPTY_FILE_CONTENT) || key.isEmpty() || key == null) {
			saveAPIKey();
			key = API_KEY;
		}
		return key;
	}

	public void check() {
		try {

			if (!path.exists()) {
				path.mkdirs();
			}

			if (!file.exists()) {

				file.createNewFile();
				this.resetApiFile(file);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void resetApiFile(File file) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);
		writer.write(EMPTY_FILE_CONTENT);
		writer.flush();
		writer.close();
	}

	public void ResetAPI() throws FileNotFoundException {
		check();
		PrintWriter writer = new PrintWriter(file);
		writer.write(JOptionPane.showInputDialog("Paste your new api key"));
		writer.flush();
		writer.close();
	}

}
