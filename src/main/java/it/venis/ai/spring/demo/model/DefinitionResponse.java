package it.venis.ai.spring.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record DefinitionResponse(
        @JsonPropertyDescription("Il lemma da definire") String lemma,
        @JsonPropertyDescription("La definizione del lemma") String description,
        @JsonPropertyDescription("Esempi di utilizzo") List<DefinitionExamples> examples) {
}
