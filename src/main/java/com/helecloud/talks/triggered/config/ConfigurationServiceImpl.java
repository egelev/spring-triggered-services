package com.helecloud.talks.triggered.config;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	private Map<String, String> configs;
	private Map<String, List<ConfigurationChangeListener>> listeners;

	public ConfigurationServiceImpl() {
		configs = new HashMap<>();
		listeners = new HashMap<>();
	}

	@Override
	public String get(String key) {
		return configs.get(key);
	}

	@Override
	public Map<String, String> getAll() {
		return new HashMap<>(configs);
	}

	@Override
	public void set(String key, String value) {
		configs.put(key, value);

		listeners.getOrDefault(key, Collections.emptyList())
		  .forEach(listener -> listener.onConfigurationChange(value));
	}

	@Override
	public void addListener(ConfigurationChangeListener listener) {
		List<ConfigurationChangeListener> list = listeners.computeIfAbsent(listener.keyToReactOn(), k -> new ArrayList<>());
		list.add(listener);
	}
}
