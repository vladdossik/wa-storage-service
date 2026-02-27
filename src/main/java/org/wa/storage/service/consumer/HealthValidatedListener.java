package org.wa.storage.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.wa.storage.service.dto.HealthMetricDto;
import org.wa.storage.service.service.HealthMetricsService;

@Slf4j
@Component
@RequiredArgsConstructor
public class HealthValidatedListener {

    private final HealthMetricsService healthMetricsService;

    @KafkaListener(topics = "${health.topics.validated}", groupId = "${spring.kafka.consumer.group-id}", concurrency = "3")
    public void receiveMetrics(HealthMetricDto metricDto) {
        log.info("Received metric from Kafka: userId={}, timestamp={}",
                metricDto.getUserId(), metricDto.getTimestamp());
        try {
            healthMetricsService.saveMetricFromDto(metricDto);
        } catch (Exception e) {
            log.error("Failed to process metric: {}", e.getMessage(), e);
            throw e;
        }
    }
}
