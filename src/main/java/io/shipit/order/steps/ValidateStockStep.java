package io.shipit.order.steps;

import io.shipit.Utils;
import io.shipit.core.PipelineStep;
import io.shipit.core.StepResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidateStockStep implements PipelineStep<ProcessOrderContext> {
    private static final Logger logger = LoggerFactory.getLogger(ValidateStockStep.class);

    @Override
    public String getStepId() {
        return OrderStepEnum.VALIDATE_STOCK.name();
    }

    @Override
    public StepResult execute(ProcessOrderContext context) {
        logger.info("Validating stock");

        Utils.sleepForOneSecond();
        return null;
    }
}
