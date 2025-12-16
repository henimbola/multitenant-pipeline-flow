package io.shipit.pipeline.processJob;

import io.shipit.pipeline.processJob.model.PipelineContext;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProcessOrderJobChargeCreditCard implements ProcessOrderJobStep {
    private static final Logger logger = Logger.getLogger(ProcessOrderJobChargeCreditCard.class.getName());

    @Override
    public String getStepId() {
        return "CHARGE_CREDIT_CARD";
    }

    @Override
    public void process(PipelineContext ctx) {
        logger.info("Charging credit card");

        sleepOneSecond();
    }
}
