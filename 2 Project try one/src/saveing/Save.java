package saveing;

import getInfoFromApi.QueryIt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Save {

	String saveFolder = "WolframApi/save/";

	public String Search(String input) throws FileNotFoundException {

		String FixedInput = input;

		FixedInput = FixedInput.replace("/", "-Slash-");
		FixedInput = FixedInput.replace("|", "-LineDown-");
		FixedInput = FixedInput.replace("\\", "-BackSlash-");

		File file = new File(saveFolder + FixedInput + ".txt");
		File folder = new File(saveFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		try {
			StringBuilder builder = new StringBuilder();

			BufferedReader reader = new BufferedReader(new FileReader(file));

			for (int i = 0; i < 100; i++) {
				String s = reader.readLine();
				builder.append(s);
				builder.append("\n");

			}
			reader.close();

			return builder.toString().replace("null", "\n");
		} catch (Exception e) {
			PrintWriter writer = new PrintWriter(file);
			String s = QueryIt.qs(input, "plaintext");
			writer.append(s);
			writer.close();
			return s;
		}
	}
}
