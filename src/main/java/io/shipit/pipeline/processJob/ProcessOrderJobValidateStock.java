package io.shipit.pipeline.processJob;

import io.shipit.pipeline.processJob.model.PipelineContext;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
public class ProcessOrderJobValidateStock implements ProcessOrderJobStep {
    private static final Logger logger = Logger.getLogger(ProcessOrderJobValidateStock.class.getName());

    @Override
    public String getStepId() {
        return "VALIDATE_STOCK";
    }

    @Override
    public void process(PipelineContext ctx) {
        logger.info("Validating stock");

        sleepOneSecond();
    }
}
