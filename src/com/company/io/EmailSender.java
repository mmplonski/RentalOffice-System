package com.company.io;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


//uzupelnij swoimi danymi do poczty
public class EmailSender {
    public static void sendEmail(String content, String emailAddress) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.wp.pl");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("asd", "asd"));
        email.setSSLOnConnect(true);
        email.setFrom("asdl");
        email.setSubject("Rental Office - reminder");
        email.setMsg(content);
        email.addTo(emailAddress);
        System.out.println("Rozpoczęto wysyłanie wiadomości email");
        email.send();
        System.out.println("Wiadomość wysłana");
    }
}
