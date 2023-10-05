import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Properties;

public class SendEmail implements Serializable {

    private String subject;
    private String sendTo;
    private final String username = "sayurangadummy@gmail.com";
    private final String password = "ubvzdcwzbsnlprxa";

    public SendEmail(String sendTo, String subject, String content){

        this.subject = subject;
        this.sendTo = sendTo;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Your Email Sent to the given Email Address.\n");

        } catch (MessagingException error) {
            error.printStackTrace();
        }
    }


    public String getSubject() {
        return subject;
    }

    public String getSendTo() {
        return sendTo;
    }
}