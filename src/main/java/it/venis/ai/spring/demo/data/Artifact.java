package it.venis.ai.spring.demo.data;

public enum Artifact {

    LIBRO("Libro"),
    FILM("Film"),
    SPETTACOLO("Spettacolo");

    private String artifact;

    Artifact(String artifact) {

        this.artifact = artifact;

    }

    public String getArtifact() {

        return artifact;

    }

}
