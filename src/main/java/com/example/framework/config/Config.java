package com.example.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class Config {
	private static final Properties properties = new Properties();

	static {
		try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (is != null) {
				properties.load(is);
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to load config.properties", e);
		}
	}

	private Config() {}

	public static String getBaseUrl() {
		return getString("baseUrl", "https://example.com");
	}

	public static String getBrowser() {
		return getString("browser", "chrome").toLowerCase();
	}

	public static boolean isHeadless() {
		return Boolean.parseBoolean(getString("headless", "false"));
	}

	private static String getString(String key, String defaultValue) {
		String sys = System.getProperty(key);
		if (sys != null && !sys.isBlank()) {
			return sys;
		}
		String env = System.getenv(key.toUpperCase());
		if (env != null && !env.isBlank()) {
			return env;
		}
		return Objects.toString(properties.getProperty(key), defaultValue);
	}
}
