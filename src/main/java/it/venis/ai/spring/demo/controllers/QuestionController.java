package it.venis.ai.spring.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import it.venis.ai.spring.demo.model.Answer;
import it.venis.ai.spring.demo.model.DefinitionRequest;
import it.venis.ai.spring.demo.model.Question;
import it.venis.ai.spring.demo.services.GeminiService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class QuestionController {

    private final GeminiService geminiService;

    public QuestionController(GeminiService geminiService) {

        this.geminiService = geminiService;

    }
    
    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        
        return geminiService.getAnswer(question);

    }

    @PostMapping("/definition")
    public Answer getDefinition(@RequestBody DefinitionRequest definitionRequest) {

        return this.geminiService.getDefinition(definitionRequest);
        
    }
    
}
