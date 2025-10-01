package it.venis.ai.spring.demo.model;

import java.util.UUID;

import it.venis.ai.spring.demo.data.Artifact;

public record ArtifactRequest(UUID id, String title, Artifact type, String review) {

    public ArtifactRequest(String title, Artifact type, String review) {

        this(UUID.randomUUID(), title, type, review);

    }

}
