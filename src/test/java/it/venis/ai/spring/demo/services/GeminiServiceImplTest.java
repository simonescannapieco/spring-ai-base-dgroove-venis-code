package it.venis.ai.spring.demo.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeminiServiceImplTest {
    
    @Autowired
    GeminiFromModelServiceImpl geminiModelService;
    
    @Autowired
    GeminiFromClientServiceImpl geminiClientService;
    
    @Test
    void testGetAnswerFromModel() {

        String answer = geminiModelService.getAnswerFromModel("Raccontami una barzelletta");
        
        System.out.println(answer);
        
    }
    
    @Test
    void testGetAnswerFromClient() {

        String answer = geminiClientService.getAnswerFromClient("Spiegami cosa sei in una unica frase");
        
        System.out.println(answer);
        
    }
}
