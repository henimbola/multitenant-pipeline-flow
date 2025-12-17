package io.shipit;

import io.shipit.config.PipelineConfigRepository;
import io.shipit.config.PipelineConfiguration;
import io.shipit.core.PipelineExecutor;
import io.shipit.core.PipelineStep;
import io.shipit.order.steps.ProcessOrderContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class OrderJobEngineService {
    private static final Logger logger = Logger.getLogger(OrderJobEngineService.class.getName());

    private final PipelineExecutor pipelineExecutor;
    private final PipelineConfigRepository pipelineConfigRepository;
    private final Map<String, PipelineStep<ProcessOrderContext>> pipelineStepsRegistry;

    public OrderJobEngineService(PipelineExecutor pipelineExecutor, PipelineConfigRepository pipelineConfigRepository, List<PipelineStep<ProcessOrderContext>> steps) {
        this.pipelineExecutor = pipelineExecutor;
        this.pipelineConfigRepository = pipelineConfigRepository;

        this.pipelineStepsRegistry = steps.stream().collect(Collectors.toUnmodifiableMap(
                PipelineStep::getStepId,
                Function.identity(),
                (existing, replacement) -> {
                    throw new IllegalStateException(String.format("Duplicate key %s", existing));
                }
        ));
    }

    public void processOrderJob(String tenantId, String jobId) {
        logger.info("Order Job Started");

        List<PipelineConfiguration> pipelineConfig = pipelineConfigRepository.findAllByTenantIdAndPipelineId(tenantId, jobId);

        pipelineExecutor.execute(
                pipelineConfig
                    .stream()
                    .map(config -> pipelineStepsRegistry.get(config.stepId()))
                    .toList(),
                new ProcessOrderContext()
       );

        logger.info("Order Job Completed");
    }
}
