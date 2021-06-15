package com.helecloud.talks.triggered.triggered.service;

import org.springframework.scheduling.Trigger;

import java.util.Optional;

public interface TriggeredService extends Runnable {

	String ONLY_ONCE_TRIGGER_KEY = "onlyOnceTriggerKey";
	String CONFIG_NOT_AVAILABLE = "triggeredServiceConfigValueNotAvailable";

	Optional<String> getKey();

	Trigger constructTrigger(String config);

}
