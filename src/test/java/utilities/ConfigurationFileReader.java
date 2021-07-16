package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationFileReader {
	
	private static Properties configFile;
	static {
		try {
			String filePath = System.getProperty("user.dir") + "/configuration.properties";
			FileInputStream input = new FileInputStream(filePath);
			configFile = new Properties();
			configFile.load(input);
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load properties file.");
		}
	}

	public static String getProperty(String keyName) {
		return configFile.getProperty(keyName);
	}

}
