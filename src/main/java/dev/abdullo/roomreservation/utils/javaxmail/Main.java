package dev.abdullo.roomreservation.utils.javaxmail;

import java.security.SecureRandom;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Main {

    public static void main(String[] args) {
        sendConfirmCodeThroughSMPT("khayrulloev.abdulloh@gmail.com");
    }
    private static Integer sendConfirmCodeThroughSMPT(String email) {
        SecureRandom sc = new SecureRandom();
        Integer confirmPassword = sc.nextInt(100000, 1000000);

        String from = "strengthnumberone@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("strengthnumberone@gmail.com", "qkqcmsawjhlwezlg");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Confirm the code you received");
            message.setText("Your code is: %d".formatted(confirmPassword));
            System.out.print("Sending...");
            Transport.send(message);
            System.out.print("\rMessage sent successfully");
            System.out.println();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return confirmPassword;
    }

}
