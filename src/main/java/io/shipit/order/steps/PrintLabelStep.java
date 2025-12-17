package io.shipit.order.steps;

import io.shipit.Utils;
import io.shipit.core.PipelineStep;
import io.shipit.core.StepResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PrintLabelStep implements PipelineStep<OrderStepEnum> {
    private static final Logger logger = LoggerFactory.getLogger(PrintLabelStep.class);

    @Override
    public OrderStepEnum getStepId() {
        return OrderStepEnum.PRINT_LABEL;
    }

    @Override
    public StepResult execute() {
        logger.info("Printing label");

        Utils.sleepForOneSecond();
        return null;
    }
}
