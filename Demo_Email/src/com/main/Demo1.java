package com.main;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *	JavaMail
 * 		Session			���ӵ�¼
 * 		MimeMessage		�ʼ�
 * 			MimeBodyPart		����
 * 			MimeMultiPart
 * 		Traspoart			�����ʼ�
 * 			send(MimeMessage m);
 * 
 * @author APPle
 */
public class Demo1 {

	public static void main(String[] args) throws Exception, MessagingException {
		
		// 1�������ʼ�������
		Properties props = new Properties();
		// 1.1 ���ӵķ��ʼ��ķ�������ַ �����ʼ���������ַ
		props.setProperty("mail.host", "smtp.163.com");
		// 1.2 ָ��������֤��¼
		props.setProperty("mail.smtp.auth", "true");
		
		// 2) ����һ��Session����,���Ӻ͵�¼������
				/**
				 *	����һ�� �������ӵ����á�
				 *	�������� ���ض��û���������base64���ܵĶ���
				 */
		Session  session = Session.getDefaultInstance(props, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("javaweb_cheng@163.com","CG0918");
					}
		});
		// �򿪵���
		session.setDebug(true);
		
		// 2���ڱ��������ϣ� ����һ���ʼ�
		MimeMessage mail = new MimeMessage(session);
		
		// 3�������ʼ�����
		// 3.1 ���÷�����
		mail.setFrom(new InternetAddress("javaweb_cheng@163.com"));
		
		// 3.2 �����ռ���
		/**
		 * 	����һ�� ���ͷ���
		 * 		���ͣ� TO    A->B
		 *      	���ͣ� CC    A->B  C
		 *      	���ͣ� BCC   A->B  C
		 *  	�������� ���͵ĵ�ַ���ռ��˵�ַ��
		 */
		mail.setRecipient(RecipientType.TO, new InternetAddress("chengbdtb@163.com"));
		
		//3.3 ��������
		mail.setSubject("JavaMail����");
		
		//3.4 ��������
		/**
		 * �������� �ʼ������ݸ�ʽ���� ��ͨ�ı���html��ʽ
		 */
		mail.setContent("�����ʼ�����������", "text/plain;charset=utf-8");
		
		//4)�����ʼ�
		Transport.send(mail);
	}
}

