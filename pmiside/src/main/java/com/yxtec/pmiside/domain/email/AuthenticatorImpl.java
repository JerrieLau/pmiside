/**
 * 
 */
package com.yxtec.pmiside.domain.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮件认证实现
 * @author Jerrie
 *
 */
public class AuthenticatorImpl extends Authenticator{

	private String username;
	private String password;
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
