package com.main;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

/**
 * 	发送带有附件的邮件
 * @author Administrator
 *
 */
public class Demo3 {
	public static void main(String[] args) throws AddressException, MessagingException, UnsupportedEncodingException {
	       // 1）配置邮件配置对象
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		
		// 2）创建Session对象
		Session session = Session.getDefaultInstance(props, new MyAuthenticator());
		session.setDebug(true);			// 打开调试
		
		// 3）配置邮件对象
		MimeMessage mail = new MimeMessage(session);
		mail.setFrom(new InternetAddress("javaweb_cheng@163.com", "湖南软件职业学院"));
		mail.setRecipient(RecipientType.TO, new InternetAddress("chengbdtb@163.com"));
		mail.setSubject("JavaEmail测试");
		mail.setContent("正文", "text/plain;charset=UTF-8");
		
		// 4）配置一个附件对象
		File file1 = new File("C:\\Users\\Administrator\\Desktop\\Cheng\\00111.jpg");
		File file2 = new File("C:\\Users\\Administrator\\Desktop\\其他.txt");
		MimeBodyPart part1 = file(file1);
		MimeBodyPart part2 = file(file2);
		
		// 5）该对象用于封装多个附件对象
		MimeMultipart multipart = new MimeMultipart();
		// 添加一个附件
		multipart.addBodyPart(part1);
		multipart.addBodyPart(part2);
		
		// 6）把全部附加封装到邮件对象
		mail.setContent(multipart);
		
		// 7）发送邮件
		Transport.send(mail);
       }

	private static MimeBodyPart file(File file) throws MessagingException,
                     UnsupportedEncodingException {
	       MimeBodyPart part = new MimeBodyPart();
		DataSource source = new FileDataSource(file);
		DataHandler handler = new DataHandler(source);
		part.setDataHandler(handler);
		// 设置文件名并设置中文编码
		part.setFileName(MimeUtility.encodeText(file.getName()));
	       return part;
       }
}