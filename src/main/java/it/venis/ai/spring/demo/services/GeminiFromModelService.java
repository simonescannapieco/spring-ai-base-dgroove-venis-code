package it.venis.ai.spring.demo.services;

import it.venis.ai.spring.demo.model.Answer;
import it.venis.ai.spring.demo.model.DefinitionRequest;
import it.venis.ai.spring.demo.model.Question;

public interface GeminiFromModelService {

    String getAnswerFromModel(String question);

    Answer getAnswerFromModel(Question question);

    Answer getDefinitionFromModel(DefinitionRequest definitionRequest);

}
