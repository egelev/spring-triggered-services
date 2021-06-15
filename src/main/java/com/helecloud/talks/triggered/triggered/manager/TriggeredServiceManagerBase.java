package com.helecloud.talks.triggered.triggered.manager;

import com.helecloud.talks.triggered.config.ConfigurationService;
import com.helecloud.talks.triggered.triggered.service.TriggeredService;
import com.helecloud.talks.triggered.triggered.manager.impl.TriggeringConfigurationChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.TaskScheduler;

import java.util.Set;

import static com.helecloud.talks.triggered.triggered.service.TriggeredService.CONFIG_NOT_AVAILABLE;

public abstract class TriggeredServiceManagerBase<T extends TriggeredService> implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger log = LoggerFactory.getLogger(TriggeredServiceManagerBase.class);

	private final ApplicationContext applicationContextToReactOn;

	private final ConfigurationService configurationService;

	protected TriggeredServiceManagerBase(
	  ApplicationContext applicationContextToReactOn,
	  ConfigurationService configurationService) {
		this.applicationContextToReactOn = applicationContextToReactOn;
		this.configurationService = configurationService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (!this.applicationContextToReactOn.getId().equals(event.getApplicationContext().getId())) {
			log.info("Skip event: {}, from application context: {}",
			  event, event.getApplicationContext().getDisplayName());
			return;
		}

		log.info("React on event: {}, from application context: {}",
		  event, event.getApplicationContext().getDisplayName());


		Set<T> services = getServices();
		log.info("{} starts {} TriggeredServices on event: {}",
		  this.getClass().getSimpleName(),
		  services.size(),
		  event);

		services.forEach(this::startService);
	}

	protected void startService(T service) {
		String configValue = service.getKey()
		  .map(configurationService::get)
		  .orElse(CONFIG_NOT_AVAILABLE);

		TriggeringConfigurationChangeListener listener = new TriggeringConfigurationChangeListener(service, getScheduler());
		configurationService.addListener(listener);

		// initial start
		listener.onConfigurationChange(configValue);
	}

	protected abstract Set<T> getServices();
	protected abstract TaskScheduler getScheduler();
}
