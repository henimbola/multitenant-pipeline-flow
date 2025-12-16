package io.shipit;

public record JobPipeline(String tenantId, int stepOrder, String jobId, String stepId) {
}
