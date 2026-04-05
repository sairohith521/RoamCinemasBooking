package BookMyShow;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailService {

    public static void sendEmail(String toEmail, String ticketDetails) {

        final String fromEmail = "sairohith520@gmail.com";
        final String password = "ioiu wnpi gtve nqil";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toEmail)
            );
            message.setSubject("Ticket Confirmation");

            message.setText("Your ticket is confirmed!\n\n" + ticketDetails);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}