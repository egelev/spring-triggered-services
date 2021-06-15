package com.helecloud.talks.triggered.config;

public interface ConfigurationChangeListener {

	String keyToReactOn();
	void onConfigurationChange(String newValue);

}
