package io.shipit.core;

public interface PipelineStep<T> {
    T getStepId();
    StepResult execute();
}
