package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail() {
        emailService.sendEmail("mehta31896@gmail.com",
                "Testing Java mail sender",
                "HI, app kaise hain ? Ye email test app se send kari gyi ha!!");
    }
 }
