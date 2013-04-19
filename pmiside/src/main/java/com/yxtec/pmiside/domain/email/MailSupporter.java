/**
 * 
 */
package com.yxtec.pmiside.domain.email;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 
 * 邮件支持
 * @author Jerrie
 * 
 */
public class MailSupporter implements IMailSupporter{

	private String server;
	private boolean needAuth;
	private String username;
	private String password;
	private String showName;
	private Authenticator auth;

	@Override
	public void sendMail(Mail mail) throws Exception{
		Session session = null;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", server);
		if (needAuth) { // 服务器需要身份认证
			 props.put("mail.smtp.auth","true");
			 session=Session.getDefaultInstance(props, auth);
		} else {
			props.put("mail.smtp.auth", "false");
			session = Session.getDefaultInstance(props, null);
		}
//		session.setDebug(true);
		Transport trans = null;
		Message msg = new MimeMessage(session);
		
		//FROM
		Address from_address = new InternetAddress(mail.getFrom(), showName);
		msg.setFrom(from_address);
		
		// TO
		InternetAddress[] address = {new InternetAddress(mail.getTo())};
		msg.setRecipients(Message.RecipientType.TO, address);
		
/*		// CC
		InternetAddress[] addrCC = {new InternetAddress(mail.getCc())};
		msg.setRecipients(Message.RecipientType.CC, addrCC);
		
		// BCC
		InternetAddress[] addrBCC = {new InternetAddress(mail.getBcc())};
		msg.setRecipients(Message.RecipientType.BCC, addrBCC); */
		
		msg.setSubject(mail.getSubject());
		Multipart mp = new MimeMultipart();
		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setContent(mail.getContent(), "text/html; charset=UTF-8");
		mp.addBodyPart(mbp);
		
		if (mail.getAttachments() != null && mail.getAttachments().size() > 0) { // 有附件
			List<String> attachments = mail.getAttachments();
			for(String i : attachments) {
				mbp = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(i);
				mbp.setDataHandler(new DataHandler(fds));
				mbp.setFileName(fds.getName()); // 得到文件名同样至入BodyPart
				mp.addBodyPart(mbp);
			}
		}
		msg.setContent(mp); // Multipart加入到信件
		msg.setSentDate(new Date()); // 设置信件头的发送日期
		// 发送信件
		msg.saveChanges();
		trans = session.getTransport("smtp");
		trans.connect(server, username, password);
		trans.sendMessage(msg, msg.getAllRecipients());
		trans.close();
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public boolean isNeedAuth() {
		return needAuth;
	}

	public void setNeedAuth(boolean needAuth) {
		this.needAuth = needAuth;
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

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public Authenticator getAuth() {
		return auth;
	}

	public void setAuth(Authenticator auth) {
		this.auth = auth;
	}

}
