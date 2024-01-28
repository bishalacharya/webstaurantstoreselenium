package com.webstaurantstore.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class to load configuration properties from a file.
 */
public class ConfigProperties {
	private static final Properties properties = new Properties();

	static {
		try (InputStream input = ConfigProperties.class.getClassLoader().getResourceAsStream("config.properties")) {
			properties.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the value of a property.
	 *
	 * @param key Property key
	 * @return Property value
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * Get the value of a property with a default value.
	 *
	 * @param key          Property key
	 * @param defaultValue Default value if the property is not found
	 * @return Property value or default value
	 */
	public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
}
