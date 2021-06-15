package com.helecloud.talks.triggered.triggered.manager.impl;

import com.helecloud.talks.triggered.config.ConfigurationService;
import com.helecloud.talks.triggered.triggered.manager.TriggeredServiceManagerBase;
import com.helecloud.talks.triggered.triggered.service.green.GreenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.helecloud.talks.triggered.boot.ThreadPoolConfig.GREEN_POOL;

@Service
public class GreenServiceManager extends TriggeredServiceManagerBase<GreenService> {

	private final ThreadPoolTaskScheduler scheduler;

	private final Set<GreenService> services;

	protected GreenServiceManager(
	  ApplicationContext applicationContextToReactOn,
	  @Qualifier(GREEN_POOL) ThreadPoolTaskScheduler scheduler,
	  ConfigurationService configurationService,
	  Set<GreenService> services) {

		super(applicationContextToReactOn, configurationService);
		this.scheduler = scheduler;
		this.services = services;
	}

	@Override
	protected Set<GreenService> getServices() {
		return services;
	}

	@Override
	protected TaskScheduler getScheduler() {
		return scheduler;
	}
}
