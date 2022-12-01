package aaa.tavern.listener;
/*
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;

import aaa.tavern.entity.Player;
import aaa.tavern.service.PlayerService;
import aaa.tavern.service.utils.OnRegistrationCompleteEvent;

public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private PlayerService playerService;
 
    @Autowired
    private MessageSource messages;
    
    @Autowired
    //private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }
    
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        Player player = event.getPlayer();
        String token = UUID.randomUUID().toString();
        playerService.createVerificationToken(player, token);
        
        String recipientAddress = player.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = event.getAppUrl() + "/regitrationConfirm?token=" + token;
        String message = messages.getMessage("message.regSucc", null, event.getLocale());
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
    
}
*/