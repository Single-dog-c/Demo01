package com.main;

import sun.misc.BASE64Encoder;

public class TestBase64 {
	
	public static void main(String[] args) {
		
		/**
		 * 	���Զ��κ��ַ������ֽ����ݽ��м���
		 */
		BASE64Encoder be = new BASE64Encoder();
		
		String name = be.encode("javaweb_cheng@163.com".getBytes());
		System.out.println(name);
		
		String password = be.encode("CG0918".getBytes());
		System.out.println(password);
	}
}
