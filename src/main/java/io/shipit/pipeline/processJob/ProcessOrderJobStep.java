package io.shipit.pipeline.processJob;

import io.shipit.pipeline.processJob.model.PipelineContext;

public interface ProcessOrderJobStep {
    default void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    String getStepId();

    void process(PipelineContext ctx);
}
