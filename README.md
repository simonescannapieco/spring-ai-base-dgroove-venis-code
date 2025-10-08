# Spring AI
## Generative Artificial Intelligence con Java - Corso base

### *Implicit* e *Zero-shot prompting* per *sentiment analysis*

- *Implicit prompting* per traduzione multilingua
    1. Creazione modello \texttt{TranslationRequest.java} per richiesta traduzione lemma
    2. Creazione \textit{prompt template} per traduzione da italiano a multilingua 
    3. Modifiche ad interfaccia ed implementazione del servizio Gemini
    4. Modifica del controllore MVC per servizio Gemini
    5. \textit{Test} delle funzionalità con Postman/Insomnia   
- *Zero-shot prompting* per *sentiment analysis*
    1. Creazione enumeratori `ArtifactType.java` e `Sentiment.java`
    2. Creazione del modello di artefatto `Artifact.java`
    3. Creazione modello richiesta per artefatto `ArtifactRequest.java`
    4. Creazione *string template* per *sentiment analysis* di tipo *0-shot*
    5. Modifiche ad interfaccia ed implementazione del servizio Gemini
    6. Modifica del controllore MVC per servizio Gemini
    7. *Test* delle funzionalità con [Postman](https://github.com/simonescannapieco/spring-ai-base-dgroove-venis-code/blob/13-spring-ai-gemini-implicit-zero-shot-prompting/doc/Spring%20AI%20-%20Corso%20base.postman_collection.json)/[Insomnia](https://github.com/simonescannapieco/spring-ai-base-dgroove-venis-code/blob/13-spring-ai-gemini-implicit-zero-shot-prompting/doc/Spring%20AI%20-%20Corso%20base.insomnia_collection.yaml)

Per ulteriori informazioni, si rimanda al [documento di esercitazione](https://github.com/simonescannapieco/spring-ai-base-dgroove-venis-slides/blob/master/practice/15_spring_ai_gemini_implicit_zero_shot_prompting/out/15_spring_ai_gemini_implicit_zero_shot_prompting_handout.pdf).
