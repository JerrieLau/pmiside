package com.yxtec.pmiside.domain.email;

import javax.mail.Authenticator;


/**
 * 邮件支持接口
 * @author Jerrie
 *
 */
public interface IMailSupporter {

	public void sendMail(Mail mail) throws Exception;
	
	public String getServer();

	public void setServer(String server);
	
	public Authenticator getAuth();

	public void setAuth(Authenticator auth);
	
	public String getUsername();

	public void setUsername(String username) ;

	public String getPassword();

	public void setPassword(String password);
	
	public boolean isNeedAuth();

	public void setNeedAuth(boolean needAuth);
	
}
