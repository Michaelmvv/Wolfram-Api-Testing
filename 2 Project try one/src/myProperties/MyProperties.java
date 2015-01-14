package myProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MyProperties {
	public void setUp() {
		Properties defaultProps = new Properties();
		try {
			FileInputStream in = new FileInputStream("defaultProperties");
			defaultProps.load(in);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
