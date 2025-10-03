package it.venis.ai.spring.demo.data;

public enum Sentiment {

    MOLTO_POSITIVO("molto positivo"),
    POSITIVO("positivo"),
    NEUTRALE("neutrale"),
    NEGATIVO("negativo"),
    MOLTO_NEGATIVO("molto negativo");

    private String sentiment;

    Sentiment(String sentiment) {

        this.sentiment = sentiment;

    }

    public String getSentiment() {

        return sentiment;

    }

}
