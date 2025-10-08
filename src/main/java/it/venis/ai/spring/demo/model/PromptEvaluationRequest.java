package it.venis.ai.spring.demo.model;

import java.util.Objects;
import java.util.UUID;

import it.venis.ai.spring.demo.data.EvaluationMetric;

public record PromptEvaluationRequest(UUID id, String prompt, EvaluationMetric metric) {

    public PromptEvaluationRequest(String prompt, EvaluationMetric metric) {
        this(UUID.randomUUID(), prompt, metric);
    }

    @Override
    public EvaluationMetric metric() {
        return Objects.requireNonNullElse(this.metric, EvaluationMetric.BLEU);
    }
    
}
