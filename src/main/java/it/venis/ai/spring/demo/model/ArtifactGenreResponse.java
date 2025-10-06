package it.venis.ai.spring.demo.model;

import it.venis.ai.spring.demo.data.ArtifactGenre;

public record ArtifactGenreResponse(ArtifactGenre genre, String reasoning) {}
