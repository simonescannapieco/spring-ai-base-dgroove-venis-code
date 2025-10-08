package it.venis.ai.spring.demo.model;

import java.util.List;

public record PromptEvaluationResponse(List<Prompt> evaluated_prompts) {
    
}
