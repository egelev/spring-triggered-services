package com.helecloud.talks.triggered.triggered.service.green;

import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LowerCaseGreenService implements GreenService {

	private String latestConfig = "";

	@Override
	public Optional<String> getKey() {
		return Optional.of("lower-green");

	}

	@Override
	public Trigger constructTrigger(String config) {
		latestConfig = config;
		return GreenService.super.constructTrigger(config);
	}

	@Override
	public void run() {

		System.out.println("green service: " + latestConfig);

	}
}
