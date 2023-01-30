package org.email;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Sendingemailusingjava {
    public static void main(String[] args) {
        String addfrom="tanvirpirjada92@gmail.com";
        String toadd="tanvirpirjada03@gmail.com";
        String ccadd="tanvirpirjada786@gmail.com";
        String mabody="Welcome to my first email sending project with java!!!";
        try {
            callSendingemailusingjava(addfrom,toadd,mabody,ccadd);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void callSendingemailusingjava(String msgfrom,String ToAdrress,String massagebody, String ccAdress) throws MessagingException {
        //creating object of system properties and this is work as hashmap
        Properties properties=System.getProperties();

        //we need this below info for connecting our local machine with server or service provider ----gmail
        //host name
        //port no
        //ssl level   secure sockets layer
        //authentication

        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","465");
        // for authorization we nees ssl
        properties.put("mail.smtp.ssl.enable","true");
        //for authentication we need authentication
        properties.put("mail.smtp.auth","true");


        // step 1  creating session for establish connection with gmail server
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tanvirpirjada92@gmail.com", Constant.PASSWORD);
            }
        });


        // step 2   compose the mail/massage
        MimeMessage massage=new MimeMessage(session);
        //from where we have to send mail
        massage.setFrom(msgfrom);
        //to who were we want to sent mail abd the type  if recipient to,cc,orbcc
        massage.addRecipients(Message.RecipientType.TO,ToAdrress);
        massage.addRecipients(Message.RecipientType.CC,ccAdress);
        massage.setSubject("My first mail sending project");
        massage.setText(massagebody);

        // step no 3  sent mail

        Transport.send(massage);
        System.out.println("email sent Succesfully");


    }
}