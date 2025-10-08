package it.venis.ai.spring.demo.data;

public enum EvaluationMetric {
    
    BLEU("BLEU (Bilingual Evaluation Understudy)"),
    ROUGE_1("ROUGE-1 (Recall-Oriented Understudy for Gisting Evaluation con sovrapposizione di unigrammi)"),
    ROUGE_2("ROUGE-2 (Recall-Oriented Understudy for Gisting Evaluation con sovrapposizione di bigrammi)"),
    ROUGE_L("ROUGE-L (Recall-Oriented Understudy for Gisting Evaluation basato su Least Common Subsumer)"),
    ROUGE_W("ROUGE-W (Recall-Oriented Understudy for Gisting Evaluation basato su Least Common Subsumer pesato)"),
    ROUGE_S("ROUGE-S (Recall-Oriented Understudy for Gisting Evaluation basato su co-occorrenza di skip-bigrammi)"),
    ROUGE_SU("ROUGE-SU (Recall-Oriented Understudy for Gisting Evaluation basato co-occorrenza di skip-bigrammi e unigrammi)");

    private String metric;

    EvaluationMetric(String metric) {

        this.metric = metric;

    }

    public String getMetric() {

        return metric;

    }

}
