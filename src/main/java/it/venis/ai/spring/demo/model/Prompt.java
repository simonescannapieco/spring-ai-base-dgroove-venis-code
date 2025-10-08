package it.venis.ai.spring.demo.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record Prompt(
    @JsonPropertyDescription("Il contenuto del prompt") String prompt, 
    @JsonPropertyDescription("Il punteggio del prompt secondo la metrica") Double evaluation_score) {
}
