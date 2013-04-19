/**
 * 
 */
package com.yxtec.pmiside.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 安全工具集
 * 
 * @author Jerrie
 * 
 */
@Component("securityUtilities")
@Scope("prototype")
public class SecurityUtilities {

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public String encrypt(String content, String password) throws Exception {
		password = md5(password);
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec securekey = new SecretKeySpec(password.getBytes("utf-8"), 0, 16, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, securekey);
		byte[] byteContent = content.getBytes("utf-8");
		byte[] result = cipher.doFinal(byteContent);
		String encryptedContent = Hex.encodeHexString(result);
		return encryptedContent;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public String decrypt(String encrptedContent, String password) throws Exception {
		byte[] encryptedBytes = Hex.decodeHex(encrptedContent.toCharArray());
		password = md5(password);
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec securekey = new SecretKeySpec(password.getBytes("utf-8"), 0, 16, "AES");
		cipher.init(Cipher.DECRYPT_MODE, securekey);// 设置密钥和解密形式
		byte[] result = cipher.doFinal(encryptedBytes);
		String decryptedContent = new String(result, "utf-8");
		return decryptedContent;// 加密
	}

	
	/**
	 * MD5
	 * @param content
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	private String md5(String content) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(content.getBytes("utf-8"));
		byte b[] = md.digest();
		int i;
		StringBuilder buf = new StringBuilder("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}
}
