package it.venis.ai.spring.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.venis.ai.spring.demo.data.ArtifactGenre;
import it.venis.ai.spring.demo.data.Sentiment;
import it.venis.ai.spring.demo.model.Answer;
import it.venis.ai.spring.demo.model.Artifact;
import it.venis.ai.spring.demo.model.ArtifactGenreResponse;
import it.venis.ai.spring.demo.model.ArtifactRequest;
import it.venis.ai.spring.demo.model.DefinitionRequest;
import it.venis.ai.spring.demo.model.DefinitionResponse;
import it.venis.ai.spring.demo.model.Prompt;
import it.venis.ai.spring.demo.model.PromptEvaluationRequest;
import it.venis.ai.spring.demo.model.PromptEvaluationResponse;
import it.venis.ai.spring.demo.model.Question;
import it.venis.ai.spring.demo.model.TranslationRequest;

@Service
public class GeminiFromClientServiceImpl implements GeminiFromClientService {

    private final ChatClient chatClient;

    public GeminiFromClientServiceImpl(ChatClient.Builder chatClientBuilder) {

        this.chatClient = chatClientBuilder.build();

    }

    @Override
    public String getAnswerFromClient(String question) {

        return this.chatClient.prompt()
                .user(question)
                .call()
                .content();

    }

    @Override
    public Answer getAnswerFromClient(Question question) {

        return new Answer(getAnswerFromClient(question.question()));

    }

    @Value("classpath:templates/get-definition-prompt.st")
    private Resource definitionPrompt;

    @Override
    public Answer getDefinitionFromClient(DefinitionRequest definitionRequest) {

        return new Answer(this.chatClient.prompt()
                .user(u -> u.text(this.definitionPrompt)
                        .params(Map.of("lemma", definitionRequest.lemma())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content());

    }

    @Value("classpath:templates/get-custom-format-definition-prompt.st")
    private Resource customFormatDefinitionPrompt;

    @Override
    public Answer getCustomFormatDefinitionFromClient(DefinitionRequest definitionRequest) {

        return new Answer(this.chatClient.prompt()
                .user(u -> u.text(this.customFormatDefinitionPrompt)
                        .params(Map.of("lemma", definitionRequest.lemma())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('<')
                        .endDelimiterToken('>')
                        .build())
                .call()
                .content());

    }

    @Value("classpath:templates/get-json-user-format-definition-prompt.st")
    private Resource JSONUserFormatDefinitionPrompt;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Answer getJSONUserFormatDefinitionFromClient(DefinitionRequest definitionRequest) {

        String chatResponse = this.chatClient.prompt()
                .user(u -> u.text(this.JSONUserFormatDefinitionPrompt)
                        .params(Map.of("lemma", definitionRequest.lemma())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('<')
                        .endDelimiterToken('>')
                        .build())
                .call()
                .content();
        
        System.out.println(chatResponse);

        String responseString;

        try {
            JsonNode rootNode = objectMapper.readTree(chatResponse.replace("`","").replaceFirst("json",""));
            responseString = objectMapper.writeValueAsString(rootNode);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new Answer(responseString);

    }


    @Value("classpath:templates/get-json-output-converter-format-definition-prompt.st")
    private Resource JSONOutputCOnverterFormatDefinitionPrompt;

    @Override
    public DefinitionResponse getJSONOutputConverterFormatDefinitionFromClient(DefinitionRequest definitionRequest) {
        
        BeanOutputConverter<DefinitionResponse> converter = new BeanOutputConverter<>(DefinitionResponse.class);

        String format = converter.getFormat();

        System.out.println(format);
        
        String chatResponse = this.chatClient.prompt()
                .user(u -> u.text(this.JSONOutputCOnverterFormatDefinitionPrompt)
                        .params(Map.of("lemma", definitionRequest.lemma(), "agente", definitionRequest.agent(), "formato", format)))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();

        return converter.convert(Objects.requireNonNull(chatResponse));

    }

    @Value("classpath:templates/get-lemma-translation-prompt.st")
    private Resource lemmaTanslationPrompt;

    @Override
    public Answer getTranslationForLemma(TranslationRequest translationRequest) {
        
        List<String> stopSequences = Stream.of(" ", "|", "\n").collect(Collectors.toList());

        String chatResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.1)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(20)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .stopSequences(stopSequences)
                .build())
                .user(u -> u.text(this.lemmaTanslationPrompt)
                        .params(Map.of("lemma", translationRequest.lemma())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();

        return new Answer(chatResponse);
    }

    @Value("classpath:templates/get-artifact-sentiment-prompt.st")
    private Resource artifactSentimentPrompt;

    @Override
    public Answer getSentimentForArtifact(ArtifactRequest artifactRequest) {
        
        List<String> stopSequences = Stream.of(" ", "\n").collect(Collectors.toList());

        Sentiment chatResponse = Sentiment.valueOf(
                this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.1)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(10)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .stopSequences(stopSequences)
                .build())
                .user(u -> u.text(this.artifactSentimentPrompt)
                        .params(Map.of("recensione", artifactRequest.artifact().body(), "artefatto", artifactRequest.artifact().type())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content()
                );

        return new Answer(chatResponse.getSentiment());

    }
    
    @Value("classpath:templates/get-ner-yaml-prompt.st")
    private Resource artifactNERYAMLPrompt;
    
    @Override
    public Answer getNERinYAMLForArtifact(ArtifactRequest artifactRequest) {
        
        String chatResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.1)
                .topP(1.0)
                //.topK(30)
                .maxTokens(250)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .user(u -> u.text(this.artifactNERYAMLPrompt)
                        .params(Map.of("titolo", artifactRequest.artifact().title(),
                                "sottotitolo", artifactRequest.artifact().subtitle(),
                                "corpo", artifactRequest.artifact().body())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();

        return new Answer(chatResponse);
    }

    @Value("classpath:templates/set-advice-system-prompt.st")
    private Resource adviceSystemPrompt;

    @Value("classpath:templates/get-advice-user-prompt.st")
    private Resource adviceUserPrompt;

    @Override
    public Answer getSuggestionForArtifact(ArtifactRequest artifactRequest) {

        String sentiment = getSentimentForArtifact(artifactRequest).answer();

        String ner = getNERinYAMLForArtifact(artifactRequest).answer();

        String chatResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.3)
                .topP(1.0)
                //.topK(30)
                .maxTokens(500)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .system(s -> s.text(this.adviceSystemPrompt)
                        .params(Map.of("sentiment", sentiment,
                                "ner", ner,
                                "artefatto", artifactRequest.artifact().type())))
                .user(u -> u.text(this.adviceUserPrompt)
                        .params(Map.of("titolo", artifactRequest.artifact().title(),
                                "sottotitolo", artifactRequest.artifact().subtitle(),
                                "corpo", artifactRequest.artifact().body())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();

        return new Answer(chatResponse);
    }

    @Value("classpath:templates/get-key-settings-for-artifact-system-prompt.st")
    private Resource keySettingsForArtifactSystemPrompt;

    @Value("classpath:templates/get-key-settings-for-artifact-user-prompt.st")
    private Resource keySettingsForArtifactUserPrompt;

    @Value("classpath:templates/get-generated-artifact-prompt.st")
    private Resource generatedArtifactPrompt;   

    @Override
    public Artifact getGeneratedArtifact(ArtifactRequest artifactRequest, Integer numChoices, Integer numParagraphs) {
        
        BeanOutputConverter<Artifact> converter = new BeanOutputConverter<>(Artifact.class);

        String listResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(1.0)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(2000)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .system(s -> s.text(this.keySettingsForArtifactSystemPrompt)
                        .params(Map.of("artefatto", artifactRequest.artifact().type().getArtifactType(),
                                "genere", artifactRequest.artifact().genre())))
                .user(u -> u.text(this.keySettingsForArtifactUserPrompt)
                        .params(Map.of("numero", numChoices)))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();       
        
        String generatedArtifact = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(1.0)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(1024)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .user(u -> u.text(this.generatedArtifactPrompt)
                        .params(Map.of("lista", listResponse,
                                "numero", numParagraphs,
                                "artefatto", artifactRequest.artifact().type(),
                                "genere", artifactRequest.artifact().genre(),
                                "formato", converter.getFormat())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();
        
                return converter.convert(generatedArtifact);
    }

    @Value("classpath:templates/get-artifact-genre-prompt.st")
    private Resource artifactGenrePrompt;    

    @Override
    public Answer getGenreForArtifact(ArtifactRequest artifactRequest) {
        
        List<ArtifactGenreResponse> responses = new ArrayList<ArtifactGenreResponse>();

        for (int i = 0; i <5; i++) {
                List<ArtifactGenreResponse> genreResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(2.0)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(1024)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .user(u -> u.text(this.artifactGenrePrompt)
                        .params(Map.of("descrizione", artifactRequest.artifact().body(),
                                "artefatto", artifactRequest.artifact().type(),
                                "generi_possibili", ArtifactGenre.values())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .entity(new ParameterizedTypeReference<List<ArtifactGenreResponse>>() {});

                responses.addAll(genreResponse);
        }
        return new Answer(responses.stream().collect(Collectors.groupingBy(s -> s.genre(), Collectors.counting())).toString());
    }

    @Value("classpath:templates/set-prompt-alternatives-system-prompt.st")
    private Resource promptAlternativesSystemPrompt;

    @Value("classpath:templates/get-prompt-alternatives-user-prompt.st")
    private Resource promptAlternativesUserPrompt;

    @Value("classpath:templates/get-ordered-prompt-alternatives-prompt.st")
    private Resource orderedPromptAlternativesPrompt; 

    @Override
    public PromptEvaluationResponse getEvaluatedPrompts(PromptEvaluationRequest promptEvaluationRequest) {

        BeanOutputConverter<PromptEvaluationResponse> converter = new BeanOutputConverter<>(PromptEvaluationResponse.class);

        String listResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(2.0)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(2000)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .system(s -> s.text(this.promptAlternativesSystemPrompt)
                        .params(Map.of("numero", 10)))
                .user(u -> u.text(this.promptAlternativesUserPrompt)
                        .params(Map.of("prompt", promptEvaluationRequest.prompt())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();
        
        String orderedListResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.0)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(2000)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .user(u -> u.text(this.orderedPromptAlternativesPrompt)
                        .params(Map.of("metrica", promptEvaluationRequest.metric().getMetric(), "lista", listResponse, "formato", converter.getFormat())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();

        return converter.convert(orderedListResponse);

    }

}
