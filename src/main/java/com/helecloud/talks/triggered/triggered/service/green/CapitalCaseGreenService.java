package com.helecloud.talks.triggered.triggered.service.green;

import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CapitalCaseGreenService implements GreenService {

	private String latestConfig = "";

	@Override
	public Optional<String> getKey() {
		return Optional.of("capital-green");
	}

	@Override
	public Trigger constructTrigger(String config) {
		latestConfig = config;
		return GreenService.super.constructTrigger(config);
	}

	@Override
	public void run() {

		System.out.println("GREEN SERVICE: " + latestConfig);

	}
}
