package com.helecloud.talks.triggered.triggered.manager.impl;

import com.helecloud.talks.triggered.config.ConfigurationService;
import com.helecloud.talks.triggered.triggered.service.blue.BlueService;
import com.helecloud.talks.triggered.triggered.manager.TriggeredServiceManagerBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.helecloud.talks.triggered.boot.ThreadPoolConfig.BLUE_POOL;

@Service
public class BlueServiceManager extends TriggeredServiceManagerBase<BlueService> {

	private final ThreadPoolTaskScheduler scheduler;

	private final Set<BlueService> services;

	protected BlueServiceManager(
	  ApplicationContext applicationContextToReactOn,
	  @Qualifier(BLUE_POOL) ThreadPoolTaskScheduler scheduler,
	  ConfigurationService configurationService,
	  Set<BlueService> services) {

		super(applicationContextToReactOn, configurationService);
		this.scheduler = scheduler;
		this.services = services;
	}

	@Override
	protected Set<BlueService> getServices() {
		return services;
	}

	@Override
	protected TaskScheduler getScheduler() {
		return scheduler;
	}

}
