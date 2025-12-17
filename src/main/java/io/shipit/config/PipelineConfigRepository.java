package io.shipit.config;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class PipelineConfigRepository {
    private List<PipelineConfiguration> data = new ArrayList<>();

    public PipelineConfigRepository() {
        data.add(new PipelineConfiguration("Mom & Pop Shom", "PROCESS_ORDER", 0, "VALIDATE_STOCK"));
        data.add(new PipelineConfiguration("Mom & Pop Shom", "PROCESS_ORDER",1, "CHARGE_CREDIT_CARD"));
        data.add(new PipelineConfiguration("Mom & Pop Shom", "PROCESS_ORDER", 2, "PRINT_LABEL"));
    }

    public List<PipelineConfiguration> findAllByTenantIdAndPipelineId(String tenantId, String pipelineId) {
        return data.stream()
                .filter(pipelineConfiguration -> pipelineConfiguration.tenantId().equals(tenantId) && pipelineConfiguration.pipelineId().equals(pipelineId))
                .sorted(Comparator.comparingInt(PipelineConfiguration::stepOrder))
                .toList();
    }
}
