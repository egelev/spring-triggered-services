package com.helecloud.talks.triggered.triggered.manager.impl;

import com.helecloud.talks.triggered.triggered.service.TriggeredService;
import com.helecloud.talks.triggered.triggered.manager.TriggeredServiceManagerBase;
import com.helecloud.talks.triggered.config.ConfigurationChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;

import java.util.concurrent.ScheduledFuture;

import static com.helecloud.talks.triggered.triggered.service.TriggeredService.ONLY_ONCE_TRIGGER_KEY;

public class TriggeringConfigurationChangeListener implements ConfigurationChangeListener {

	private static final Logger log = LoggerFactory.getLogger(TriggeredServiceManagerBase.class);

	private ScheduledFuture<?> future;

	private final TriggeredService service;
	private final TaskScheduler taskScheduler;

	public TriggeringConfigurationChangeListener(TriggeredService service, TaskScheduler taskScheduler) {
		this.service = service;
		this.taskScheduler = taskScheduler;
	}

	@Override
	public String keyToReactOn() {
		return service.getKey().orElse(ONLY_ONCE_TRIGGER_KEY);
	}

	@Override
	public void onConfigurationChange(String newValue) {
		if (future != null) {
			future.cancel(false);
		}

		Trigger trigger = service.constructTrigger(newValue);
		future = taskScheduler.schedule(service, trigger);
	}
}
