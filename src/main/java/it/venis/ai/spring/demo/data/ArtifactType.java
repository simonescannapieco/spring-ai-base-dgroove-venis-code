package it.venis.ai.spring.demo.data;

public enum ArtifactType {

    LIBRO("Libro"),
    FILM("Film"),
    SPETTACOLO("Spettacolo"),
    ARTICOLO("Articolo");

    private String artifactType;

    ArtifactType(String artifactType) {

        this.artifactType = artifactType;

    }

    public String getArtifactType() {

        return artifactType;

    }

}
