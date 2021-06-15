package com.helecloud.talks.triggered.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadPoolConfig {

	public static final String BLUE_POOL = "blueTaskScheduler";
	public static final String GREEN_POOL = "greenTaskScheduler";

	@Bean(name = BLUE_POOL, destroyMethod = "shutdown")
	public ThreadPoolTaskScheduler bluePool() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(2);
		scheduler.setThreadNamePrefix(BLUE_POOL + "-");
		scheduler.setAwaitTerminationSeconds(60);
		return scheduler;
	}


	@Bean(name = GREEN_POOL, destroyMethod = "shutdown")
	public ThreadPoolTaskScheduler greenPool() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(2);
		scheduler.setThreadNamePrefix(GREEN_POOL + "-");
		scheduler.setAwaitTerminationSeconds(60);
		return scheduler;
	}
}
