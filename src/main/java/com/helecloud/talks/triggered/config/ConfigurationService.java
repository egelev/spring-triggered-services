package com.helecloud.talks.triggered.config;

import java.util.Map;

public interface ConfigurationService {

	String get(String key);
	Map<String, String> getAll();
	void set(String key, String value);
	void addListener(ConfigurationChangeListener listener);

}
