package it.venis.ai.spring.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import it.venis.ai.spring.demo.model.Answer;
import it.venis.ai.spring.demo.model.Artifact;
import it.venis.ai.spring.demo.model.ArtifactRequest;
import it.venis.ai.spring.demo.model.DefinitionRequest;
import it.venis.ai.spring.demo.model.DefinitionResponse;
import it.venis.ai.spring.demo.model.PromptEvaluationRequest;
import it.venis.ai.spring.demo.model.PromptEvaluationResponse;
import it.venis.ai.spring.demo.model.Question;
import it.venis.ai.spring.demo.model.TranslationRequest;
import it.venis.ai.spring.demo.services.GeminiFromClientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class QuestionFromClientController {

    private final GeminiFromClientService geminiService;

    public QuestionFromClientController(GeminiFromClientService geminiService) {

        this.geminiService = geminiService;

    }

    @PostMapping("/client/ask")
    public Answer askQuestionFromClient(@RequestBody Question question) {

        return this.geminiService.getAnswerFromClient(question);

    }

    @PostMapping("/client/definition")
    public Answer getDefinitionFromClient(@RequestBody DefinitionRequest definitionRequest) {

        return this.geminiService.getDefinitionFromClient(definitionRequest);

    }

    @PostMapping("/client/definition/custom")
    public Answer getCustomFormatDefinition(@RequestBody DefinitionRequest definitionRequest) {

        return this.geminiService.getCustomFormatDefinitionFromClient(definitionRequest);

    }

    @PostMapping("/client/definition/json/user")
    public Answer getJSONUserFormatDefinition(@RequestBody DefinitionRequest definitionRequest) {

        return this.geminiService.getJSONUserFormatDefinitionFromClient(definitionRequest);

    }

    @PostMapping("/client/definition/json/converter")
    public DefinitionResponse getJSONOutputConverterFormatDefinition(@RequestBody DefinitionRequest definitionRequest) {

        return this.geminiService.getJSONOutputConverterFormatDefinitionFromClient(definitionRequest);

    }

    @PostMapping("/client/translate")
    public Answer getTranslationForLemma(@RequestBody TranslationRequest translationRequest) {

        return this.geminiService.getTranslationForLemma(translationRequest);

    }

    @PostMapping("/client/sentiment")
    public Answer getSentiment(@RequestBody ArtifactRequest artifactRequest) {

        return this.geminiService.getSentimentForArtifact(artifactRequest);

    }

    @PostMapping("/client/ner/yaml")
    public Answer getNERinYAML(@RequestBody ArtifactRequest artifactRequest) {

        return this.geminiService.getNERinYAMLForArtifact(artifactRequest);

    }

    @PostMapping("/client/advice")
    public Answer getSuggestionForArtifact(@RequestBody ArtifactRequest artifactRequest) {

        return this.geminiService.getSuggestionForArtifact(artifactRequest);

    }

    @PostMapping("/client/generate")
    public Artifact getGeneratedArtifact(@RequestBody ArtifactRequest artifactRequest,
            @RequestParam(required = true, defaultValue = "3") Integer numChoices,
            @RequestParam(required = true, defaultValue = "1") Integer numParagraphs) {

        return this.geminiService.getGeneratedArtifact(artifactRequest, numChoices, numParagraphs);

    }

    @PostMapping("/client/genre")
    public Answer getGeneratedArtifact(@RequestBody ArtifactRequest artifactRequest) {

        return this.geminiService.getGenreForArtifact(artifactRequest);

    }

    @PostMapping("/client/evaluate")
    public PromptEvaluationResponse getEvaluatedPrompts(@RequestBody PromptEvaluationRequest promptEvaluationRequest) {

        return this.geminiService.getEvaluatedPrompts(promptEvaluationRequest);

    }

}
