package top.what_can_i_say.util;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 发送邮件的
 * */
public class MyEmail {

    /**
     * 发送注册验证链接的
     * */
    public static void sendRegisterLinkbyQQ1147057783(String email,String link) throws MessagingException, UnsupportedEncodingException {
        String from = "1147057783@qq.com";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host","smtp.qq.com");
        properties.put("mail.smtp.port",587);
        properties.put("mail.smtp.auth","true");
        properties.put("mail.debug","true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1147057783@qq.com","dlenxaolhcfsijcf");
            }
        });
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(
//                使用昵称
                MimeUtility.encodeText(
                "what-can-i-say.top的大当家")+"<"+from+">"));
        mimeMessage.addRecipient(MimeMessage.RecipientType.TO,new InternetAddress(email));
        mimeMessage.setSubject("欢迎加入what-can-i-say.top");
//        如果需要发送链接，就加入http
        mimeMessage.setContent(
                "<h3>请点击下面的链接进行注册</h3>" +
                "<a href=\"http://"+link+"\">"+link+"</a>","text/html;charset=utf8");
        Transport.send(mimeMessage);
    }

}
