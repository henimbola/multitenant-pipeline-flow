package io.shipit;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class JobPipelineRepository {
    private static List<JobPipeline> data = new ArrayList<>();

    JobPipelineRepository() {
        data.add(new JobPipeline("Mom & Pop Shom", 0, "PROCESS_ORDER", "VALIDATE_STOCK"));
        //data.add(new JobPipeline("Mom & Pop Shom", 1, "PROCESS_ORDER", "CHARGE_CREDIT_CARD"));
        data.add(new JobPipeline("Mom & Pop Shom", 2, "PROCESS_ORDER", "PRINT_LABEL"));
    }

    public List<JobPipeline> findAllByTenantIdAndJobIdOrderByStepOrder(String tenantId, String jobId) {
        return data.stream()
                .filter(jobPipeline -> jobPipeline.tenantId().equals(tenantId) && jobPipeline.jobId().equals(jobId))
                .sorted(Comparator.comparingInt(JobPipeline::stepOrder))
                .toList();
    }
}
