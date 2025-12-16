package io.shipit.pipeline.processJob;

import io.shipit.pipeline.processJob.model.PipelineContext;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProcessOrderJobPrintLabel implements ProcessOrderJobStep {
    private static final Logger logger = Logger.getLogger(ProcessOrderJobPrintLabel.class.getName());

    @Override
    public String getStepId() {
        return "PRINT_LABEL";
    }

    @Override
    public void process(PipelineContext ctx) {
        logger.info("Printing label");
        sleepOneSecond();
    }
}
