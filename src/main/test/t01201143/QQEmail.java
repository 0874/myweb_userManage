package t01201143;
/**
 * 恶作剧：：163,126,sina,ali,qq,这些邮箱，每个注册10个邮箱，轮番发送邮件
 * 
 * */
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.sound.sampled.Port;

public class QQEmail {

	public static void main(String[] args) throws AddressException, MessagingException, InterruptedException {
		// TODO Auto-generated method stub
		String to = "java_is_so_easy@outlook.com";
		String from = "1147057783@qq.com";
		String host = "smtp.qq.com";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug","true");
		
		Session session = Session.getDefaultInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("1147057783@qq.com", "dlenxaolhcfsijcf");
			}
		});
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
		message.setSubject("sub");
		message.setText("text");
//		for(int i=0;i<100;i++) {
//			System.out.println("发送邮件次数：：：：：：：：：：：：："+i);
			Transport.send(message);
//			message.setText(""+i);
//			message.setSubject("ss"+i);
//			Thread.sleep(5);
//		}
	}

}
