package it.venis.ai.spring.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import it.venis.ai.spring.demo.model.Answer;
import it.venis.ai.spring.demo.model.DefinitionRequest;
import it.venis.ai.spring.demo.model.Question;
import it.venis.ai.spring.demo.services.GeminiFromModelService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class QuestionFromModelController {

    private final GeminiFromModelService geminiService;

    public QuestionFromModelController(GeminiFromModelService geminiService) {

        this.geminiService = geminiService;

    }

    @PostMapping("/model/ask")
    public Answer askQuestion(@RequestBody Question question) {

        return this.geminiService.getAnswerFromModel(question);

    }

    @PostMapping("/model/definition")
    public Answer getDefinition(@RequestBody DefinitionRequest definitionRequest) {

        return this.geminiService.getDefinitionFromModel(definitionRequest);

    }

}
