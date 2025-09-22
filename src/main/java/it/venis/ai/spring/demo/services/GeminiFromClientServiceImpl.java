package it.venis.ai.spring.demo.services;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.venis.ai.spring.demo.model.Answer;
import it.venis.ai.spring.demo.model.DefinitionRequest;
import it.venis.ai.spring.demo.model.Question;

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

    @Value("classpath:templates/get-json-format-definition-prompt.st")
    private Resource JSONFormatDefinitionPrompt;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Answer getJSONFormatDefinitionFromClient(DefinitionRequest definitionRequest) {

        String chatResponse = this.chatClient.prompt()
                .user(u -> u.text(this.JSONFormatDefinitionPrompt)
                        .params(Map.of("lemma", definitionRequest.lemma())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('<')
                        .endDelimiterToken('>')
                        .build())
                .call()
                .content();
        
        System.out.println(chatResponse);

        String responseString;

        try {
            JsonNode jsonNode = objectMapper.readTree(chatResponse.replace("`","").replaceFirst("json",""));
            responseString = jsonNode.get("definizione").asText();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new Answer(responseString);

    }

}
