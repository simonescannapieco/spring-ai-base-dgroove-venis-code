package it.venis.ai.spring.demo.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import it.venis.ai.spring.demo.model.Answer;
import it.venis.ai.spring.demo.model.Question;

@Service
public class GeminiServiceImpl implements GeminiService {

    private final ChatModel chatModel;

    public GeminiServiceImpl(ChatModel chatModel) {

        this.chatModel = chatModel;
        
    }

    @Override
    public String getAnswer(String question) {

        PromptTemplate promptTemplate = new PromptTemplate(question);

        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);

        return response.getResult().getOutput().getText();

    }

    @Override
    public Answer getAnswer(Question question) {

        return new Answer(question.question());
    
    }

}
