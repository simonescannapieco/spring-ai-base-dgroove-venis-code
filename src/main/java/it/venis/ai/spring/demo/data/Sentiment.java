package it.venis.ai.spring.demo.data;

public enum Sentiment {

    ESTREMAMENTE_POSITIVO("Estremamente positivo"),
    POSITIVO("Positivo"),
    NEUTRALE("Neutrale"),
    NEGATIVO("Negativo"),
    ESTREMAMENTE_NEGATIVO("Estremamente negativo");

    private String sentiment;

    Sentiment(String sentiment) {

        this.sentiment = sentiment;

    }

    public String getSentiment() {

        return sentiment;

    }

}