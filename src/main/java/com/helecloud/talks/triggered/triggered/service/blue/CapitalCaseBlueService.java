package com.helecloud.talks.triggered.triggered.service.blue;

import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CapitalCaseBlueService implements BlueService {

	private String latestConfig = "";

	@Override
	public Optional<String> getKey() {
		return Optional.of("capital-blue");
	}

	@Override
	public Trigger constructTrigger(String config) {
		latestConfig = config;
		return BlueService.super.constructTrigger(config);
	}

	@Override
	public void run() {

		System.out.println("BLUE SERVICE: " + latestConfig);

	}
}
