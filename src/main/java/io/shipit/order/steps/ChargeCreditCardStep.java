package io.shipit.order.steps;

import io.shipit.Utils;
import io.shipit.core.PipelineStep;
import io.shipit.core.StepResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChargeCreditCardStep implements PipelineStep<ProcessOrderContext> {
    private static final Logger logger = LoggerFactory.getLogger(ChargeCreditCardStep.class);

    @Override
    public String getStepId() {
        return OrderStepEnum.CHARGE_CREDIT_CARD.name();
    }

    @Override
    public StepResult execute(ProcessOrderContext context) {
        logger.info("Charging credit card");

        Utils.sleepForOneSecond();
        return null;
    }
}
