package com.helecloud.talks.triggered.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

public interface ConfigurationController {

	@GetMapping
	default String home() {
		return "Configuration Service Controller";
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT}, path = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
	void update(@RequestBody Map<String, String> keyValues);

	@GetMapping(path = "/get/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> get(@PathVariable String key);

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> get();
}
