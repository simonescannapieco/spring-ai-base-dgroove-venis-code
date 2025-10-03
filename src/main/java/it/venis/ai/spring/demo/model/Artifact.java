package it.venis.ai.spring.demo.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import it.venis.ai.spring.demo.data.ArtifactType;

public record Artifact(@JsonPropertyDescription("Il titolo dell'opera") String title, 
                       @JsonPropertyDescription("Il sottotitolo dell'opera") String subtitle, 
                       @JsonPropertyDescription("Il tipo dell'opera") ArtifactType type, 
                       @JsonPropertyDescription("Il genere dell'opera") String genre, 
                       @JsonPropertyDescription("La trama o la recenzione dell'opera") String body) {

    @Override
    public String title() {

        return Objects.requireNonNullElse(this.title, "---");

    }

    @Override
    public String subtitle() {

        return Objects.requireNonNullElse(this.subtitle, "---");

    }

    @Override
    public String genre() {

        return Objects.requireNonNullElse(this.genre, "non specificato");

    }

}
