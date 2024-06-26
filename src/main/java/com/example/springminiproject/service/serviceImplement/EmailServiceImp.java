package com.example.springminiproject.service.serviceImplement;


import com.example.springminiproject.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class EmailServiceImp implements EmailService {

    private final JavaMailSender sender;
    private final TemplateEngine templateEngine;
    private final Context context;
    @Override
    public void send(String email, String otp) throws MessagingException {
        context.setVariable("code",otp);
        String processedString = templateEngine.process("email", context);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
        helper.setFrom("thy.sopheak098@gmail.com");
        helper.setTo(email);
        helper.setSubject("Code Verify");
        helper.setText(processedString,true);
        sender.send(message);
    }
}
