package it.venis.ai.spring.demo.model;

import java.util.Objects;
import java.util.UUID;

public record DefinitionRequest(UUID id, String lemma, String agent) {

    public DefinitionRequest(String lemma, String agent) {
        this(UUID.randomUUID(), lemma, agent);
    }

    @Override
    public String lemma() {
        return Objects.requireNonNullElse(this.lemma, "Ciao");
    }

    @Override
    public String agent() {
        return Objects.requireNonNullElse(this.agent, "Enciclopedia Treccani");
    }

}
