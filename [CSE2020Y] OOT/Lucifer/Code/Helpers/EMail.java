package Helpers;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Vidush H. Namah on 12 Mar 2016.
 **/

public class EMail {

    //TODO: Add a sender email address + password
    private static String Sender = "vnbytes@outlook.com";
    private static String Password = "*Fg9&YYYKMA)AtuiYwsl&6St";

    //HOW TO CALL IT
    //EMail.sendMail("person@email.com", "JAVA Email Test", "This was sent from a Java Application.");

    public static void sendMail(String Receiver, String Subject, String EmailBody) throws Exception{
        String Signature = "\n\n--\n" +
                "Vidush H. Namah\n" +
                "Aspiring IT Professional\n" +
                "\n" +
                "R&D and Design Department\n" +
                "Microsoft Student Partners\n" +
                "Mauritius\n" +
                "\n" +
                "Email: vnbytes@outlook.com\n" +
                "Blog: http://www.vnbytes.me/";

        Properties P;

        P = System.getProperties();

        //YAHOO
        //P.put("mail.smtp.host", "smtp.mail.yahoo.com:);

        //GMAIL
        //P.put("mail.smtp.host", "smtp.gmail.com");

        //OUTLOOK
        P.put("mail.smtp.host", "smtp-mail.outlook.com");
        P.put("mail.smtp.auth", "true");
        P.put("mail.smtp.starttls.enable", "true");

        Session mailSession = Session.getInstance(P, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Sender, Password);
            }
        });

        Message Mail = new MimeMessage(mailSession);

        //SETTING EMAIL HEADERS
        Mail.setFrom(new InternetAddress(Sender));
        Mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Receiver));
        Mail.setSentDate(new Date());
        Mail.setSubject(Subject);

        //SETTING EMAIL BODY
        Mail.setText(EmailBody + Signature);

        //SENDING EMAIL
        Transport.send(Mail);

        System.out.println("MAIL SENT SUCCESSFULLY.");
    }

}
