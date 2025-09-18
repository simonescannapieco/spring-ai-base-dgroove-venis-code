package it.venis.ai.spring.demo.services;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import it.venis.ai.spring.demo.model.Answer;
import it.venis.ai.spring.demo.model.DefinitionRequest;
import it.venis.ai.spring.demo.model.Question;

@Service
public class GeminiFromModelServiceImpl implements GeminiFromModelService {

    private final ChatModel chatModel;

    public GeminiFromModelServiceImpl(ChatModel chatModel) {

        this.chatModel = chatModel;

    }

    @Override
    public String getAnswerFromModel(String question) {

        PromptTemplate promptTemplate = new PromptTemplate(question);

        Prompt prompt = promptTemplate.create();

        ChatResponse response = this.chatModel.call(prompt);

        return response.getResult().getOutput().getText();

    }

    @Override
    public Answer getAnswerFromModel(Question question) {

        return new Answer(getAnswerFromModel(question.question()));

    }

    @Value("classpath:templates/get-definition-prompt.st")
    private Resource definitionPrompt;

    @Override
    public Answer getDefinitionFromModel(DefinitionRequest definitionRequest) {

        PromptTemplate promptTemplate = new PromptTemplate(this.definitionPrompt);

        Prompt prompt = promptTemplate.create(Map.of("lemma", definitionRequest.lemma()));

        ChatResponse response = this.chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getText());

    }

}
