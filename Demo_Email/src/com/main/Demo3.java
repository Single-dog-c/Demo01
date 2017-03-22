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
 * 	���ʹ��и������ʼ�
 * @author Administrator
 *
 */
public class Demo3 {
	public static void main(String[] args) throws AddressException, MessagingException, UnsupportedEncodingException {
	       // 1�������ʼ����ö���
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		
		// 2������Session����
		Session session = Session.getDefaultInstance(props, new MyAuthenticator());
		session.setDebug(true);			// �򿪵���
		
		// 3�������ʼ�����
		MimeMessage mail = new MimeMessage(session);
		mail.setFrom(new InternetAddress("javaweb_cheng@163.com", "�������ְҵѧԺ"));
		mail.setRecipient(RecipientType.TO, new InternetAddress("chengbdtb@163.com"));
		mail.setSubject("JavaEmail����");
		mail.setContent("����", "text/plain;charset=UTF-8");
		
		// 4������һ����������
		File file1 = new File("C:\\Users\\Administrator\\Desktop\\Cheng\\00111.jpg");
		File file2 = new File("C:\\Users\\Administrator\\Desktop\\����.txt");
		MimeBodyPart part1 = file(file1);
		MimeBodyPart part2 = file(file2);
		
		// 5���ö������ڷ�װ�����������
		MimeMultipart multipart = new MimeMultipart();
		// ���һ������
		multipart.addBodyPart(part1);
		multipart.addBodyPart(part2);
		
		// 6����ȫ�����ӷ�װ���ʼ�����
		mail.setContent(multipart);
		
		// 7�������ʼ�
		Transport.send(mail);
       }

	private static MimeBodyPart file(File file) throws MessagingException,
                     UnsupportedEncodingException {
	       MimeBodyPart part = new MimeBodyPart();
		DataSource source = new FileDataSource(file);
		DataHandler handler = new DataHandler(source);
		part.setDataHandler(handler);
		// �����ļ������������ı���
		part.setFileName(MimeUtility.encodeText(file.getName()));
	       return part;
       }
}