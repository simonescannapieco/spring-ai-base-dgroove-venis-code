package it.venis.ai.spring.demo.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeminiServiceImplTest {
    
    @Autowired
    GeminiServiceImpl geminiService;

    @Test
    void testGetAnswer() {

        String answer = geminiService.getAnswer("Cosa è Spring AI?");
        
        System.out.println(answer);
        
    }
    
}
