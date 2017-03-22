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
 * 		Session			连接登录
 * 		MimeMessage		邮件
 * 			MimeBodyPart		附件
 * 			MimeMultiPart
 * 		Traspoart			发送邮件
 * 			send(MimeMessage m);
 * 
 * @author APPle
 */
public class Demo1 {

	public static void main(String[] args) throws Exception, MessagingException {
		
		// 1）创建邮件配置类
		Properties props = new Properties();
		// 1.1 连接的发邮件的服务器地址 设置邮件服务器地址
		props.setProperty("mail.host", "smtp.163.com");
		// 1.2 指定进行验证登录
		props.setProperty("mail.smtp.auth", "true");
		
		// 2) 创建一个Session对象,连接和登录服务器
				/**
				 *	参数一： 本次连接的配置。
				 *	参数二： 返回对用户名和密码base64加密的对象
				 */
		Session  session = Session.getDefaultInstance(props, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("javaweb_cheng@163.com","CG0918");
					}
		});
		// 打开调试
		session.setDebug(true);
		
		// 2）在本次连接上， 创建一封邮件
		MimeMessage mail = new MimeMessage(session);
		
		// 3）设置邮件内容
		// 3.1 设置发件人
		mail.setFrom(new InternetAddress("javaweb_cheng@163.com"));
		
		// 3.2 设置收件人
		/**
		 * 	参数一： 发送方法
		 * 		发送： TO    A->B
		 *      	抄送： CC    A->B  C
		 *      	密送： BCC   A->B  C
		 *  	参数二： 发送的地址（收件人地址）
		 */
		mail.setRecipient(RecipientType.TO, new InternetAddress("chengbdtb@163.com"));
		
		//3.3 设置主题
		mail.setSubject("JavaMail测试");
		
		//3.4 设置内容
		/**
		 * 参数二： 邮件的内容格式。如 普通文本，html方式
		 */
		mail.setContent("这是邮件的正文内容", "text/plain;charset=utf-8");
		
		//4)发送邮件
		Transport.send(mail);
	}
}

