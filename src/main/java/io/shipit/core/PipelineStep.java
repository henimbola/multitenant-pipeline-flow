package io.shipit.core;

public interface PipelineStep<C extends PipelineContext> {
    String getStepId();
    StepResult execute(C context);
}
