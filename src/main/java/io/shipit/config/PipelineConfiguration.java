package io.shipit.config;

public record PipelineConfiguration (
    String tenantId,
    String pipelineId,
    int stepOrder,
    String stepId
) {
}
