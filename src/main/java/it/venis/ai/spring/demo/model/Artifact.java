package it.venis.ai.spring.demo.model;

import java.util.Objects;

import it.venis.ai.spring.demo.data.ArtifactType;

public record Artifact(String title, String subtitle, ArtifactType type, String body) {
    
    @Override
    public String title() {
        return Objects.requireNonNullElse(this.title, "---");
    }

    @Override
    public String subtitle() {
        return Objects.requireNonNullElse(this.subtitle, "---");
    }   

}
