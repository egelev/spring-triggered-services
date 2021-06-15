package com.helecloud.talks.triggered.controller.rest;

import com.helecloud.talks.triggered.config.ConfigurationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ConfigurationControllerRest implements ConfigurationController {

	private final ConfigurationService configurationService;

	public ConfigurationControllerRest(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@Override
	public void update(Map<String, String> keyValues) {
		keyValues
		  .forEach((k, v) -> configurationService.set(k, v));
	}

	@Override
	public Map<String, String> get(String key) {
		return Collections.singletonMap(key, configurationService.get(key));
	}

	@Override
	public Map<String, String> get() {
		return configurationService.getAll();
	}
}
