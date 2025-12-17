package io.shipit.core;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PipelineExecutor {

    public <C extends PipelineContext> void execute(List<PipelineStep<C>> steps, C ctx) {
        for (PipelineStep<C> step : steps) {
            step.execute(ctx);
        }
    }
}
