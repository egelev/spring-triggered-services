package com.helecloud.talks.triggered.triggered.service.green;

import com.helecloud.talks.triggered.triggered.service.TriggeredService;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.CronTrigger;

import static java.util.Objects.isNull;

public interface GreenService extends TriggeredService {

	@Override
	default Trigger constructTrigger(String config) {
		if (isNull(config) || !CronExpression.isValidExpression(config)) {
			return new CronTrigger(defaultCron());
		}
		return new CronTrigger(config);
	}

	default String defaultCron() {
		return "0/10 * * * * *";
	}

}
