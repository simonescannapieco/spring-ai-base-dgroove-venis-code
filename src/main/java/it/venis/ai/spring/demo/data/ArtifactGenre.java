package it.venis.ai.spring.demo.data;

public enum ArtifactGenre {
    
    STORIA("Storico"),
    FANTASCIENZA("Fantascienza"),
    FANTASY("Fantasy"),
    AVVENTURA("Avventura"),
    FORMAZIONE("Formazione"),
    APPENDICE("Appendice"),
    RAGAZZI("Per ragazzi"),
    ORRORE("Orrore"),
    AZIONE("Azione"),
    SPY_STORY("Spy story"),
    THRILLER("Thriller"),
    GIALLO("Giallo"),
    NOIR("Noir"),
    HARD_BOILED("Hard boiled"),
    SOFT_BOILED("Soft bolied"),
    POLIZIESCO("Poliziesco"),
    ROSA("Rosa"),
    EROTICO("Erotico"),
    PSICOLOGICO("Psicologico"),
    UMORISTICO("Umoristico"),
    SOCIALE("Sociale"),
    EPISTOLARE("Epistolare");

    private String artifactGenre;

    ArtifactGenre(String artifactGenre) {

        this.artifactGenre = artifactGenre;

    }

    public String getArtifactGenre() {

        return artifactGenre;

    }

}
