package io.shipit.core;

import java.time.Instant;

public interface PipelineContext {
    <T> void set(String key, T value);
    <T> T get(String key, Class<T> type);
    <T> T getOrDefault(String key, Class<T> type, T defaultValue);

    String getTenantId();

    String getPipelineId();
    Instant getStartTime();
}
