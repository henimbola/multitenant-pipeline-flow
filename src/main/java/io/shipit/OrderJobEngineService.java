package io.shipit;

import io.shipit.pipeline.processJob.ProcessOrderJobStep;
import io.shipit.pipeline.processJob.model.OrderProcessJobContext;
import io.shipit.pipeline.processJob.model.PipelineContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class OrderJobEngineService {
    private static final Logger logger = Logger.getLogger(OrderJobEngineService.class.getName());

    private final JobPipelineRepository jobPipelineRepository;

    private final Map<String, ProcessOrderJobStep> stepsRegistry;

    public OrderJobEngineService(JobPipelineRepository jobPipelineRepository, List<ProcessOrderJobStep> steps) {
        this.jobPipelineRepository = jobPipelineRepository;

        this.stepsRegistry = steps.stream().collect(Collectors.toUnmodifiableMap(
                step -> step.getStepId().toUpperCase(),
                Function.identity(),
                (existing, replacement) -> {
                    throw new IllegalStateException(String.format("Duplicate key %s", existing));
                }
        ));
    }

    public void processOrderJob(String tenantId, String jobId) {
        logger.info("Order Job Started");

        List<JobPipeline> pipelineConfig = jobPipelineRepository.findAllByTenantIdAndJobIdOrderByStepOrder(tenantId, jobId);
        PipelineContext ctx = new OrderProcessJobContext();

        if (pipelineConfig.isEmpty()) {
            logger.info("No steps found for the job");
            return;
        }

        for (JobPipeline config : pipelineConfig) {
            String stepId = config.stepId().toUpperCase();

            ProcessOrderJobStep step = stepsRegistry.get(stepId);

            if (step != null) {
                try {
                    logger.info(String.format("Processing step %s", stepId));
                    step.process(ctx);
                } catch (Exception e) {
                    logger.severe(String.format("Error processing step %s", stepId));
                    throw e;
                }
            } else {
                logger.warning(String.format("Step %s not found", stepId));
            }
        }

        logger.info("Order Job Completed");
    }
}
