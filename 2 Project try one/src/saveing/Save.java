package saveing;

import getInfoFromApi.QueryIt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URISyntaxException;

import starter.Start;

public class Save {

	String saveFolder = "WolframApi/save/";

	public void DelFiles() {
		System.out.println("Clearing files");

		File file = new File(saveFolder);
		try {
			Runtime.getRuntime().exec("rm -R " + file.getPath());
		} catch (IOException e) {
			System.out.println("Stuff Happend");
			e.printStackTrace();
		}
	}

	public void Elements() throws IOException {
		for (int i = 0; i < 118; i++) {
			Search("Element " + i, false);
		}
	}

	public String Search(String input, Boolean caseSenc) throws IOException {
		if (input.equalsIgnoreCase("AllElements")) {
			Elements();
			return "Loading All dem Elements";
		}
		
		if (input.equalsIgnoreCase("Why Did Michael Make this?")
				|| input.equalsIgnoreCase("Why Did Michael Make this")) {
			
			try {
				StringBuilder builder = new StringBuilder();
				BufferedReader reader = new BufferedReader(new FileReader(new File(Start.class.getClassLoader().getResource("Texts/why.txt").toURI())));
				LineNumberReader lnr = new LineNumberReader(new FileReader(new File(Start.class.getClassLoader().getResource("Texts/why.txt").toURI())));
				lnr.skip(Long.MAX_VALUE);
				for (int i = 0; i < lnr.getLineNumber()+1; i++) {
					builder.append(reader.readLine() + "\n");
				}
				reader.close();
				lnr.close();

				return builder.toString();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return "IDK WHY... DONT ASK ME";
		}
		String FixedInput;
		if (caseSenc) {
			FixedInput = String.format("%040x",
					new BigInteger(1, input.getBytes()));
		} else {
			FixedInput = String.format("%040x", new BigInteger(1, input
					.toLowerCase().getBytes()));
			input = input.toLowerCase();
		}

		File file = new File(saveFolder + FixedInput);
		File folder = new File(saveFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		try {
			StringBuilder builder = new StringBuilder();

			BufferedReader reader = new BufferedReader(new FileReader(file));
			LineNumberReader lnr = new LineNumberReader(new FileReader(file));
			System.out.println("Skiping: " + lnr.skip(Long.MAX_VALUE));
			System.out.println(lnr.getLineNumber() + 1);

			for (int i = 0; i < lnr.getLineNumber(); i++) {
				String s = reader.readLine();
				builder.append(s);
				builder.append("\n");

			}
			reader.close();
			lnr.close();

			return builder.toString().replace("null", "\n");
		} catch (Exception e) {
			String s = QueryIt.qs(input, "plaintext");
			if (!s.startsWith("error")) {
				if (!folder.exists()) {
					folder.mkdirs();
				}
				System.out.println("Saveing to: " + file.getPath());
				PrintWriter writer = new PrintWriter(file);
				writer.append(s);
				writer.close();
			}

			return s;
		}
	}
}
