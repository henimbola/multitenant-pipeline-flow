package io.shipit.core;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PipelineExecutor {

    public <T> void execute(List<PipelineStep<T>> steps) {
        for (PipelineStep<T> step : steps) {
            step.execute();
        }
    }
}
