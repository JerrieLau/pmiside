package com.yxtec.pmiside.domain.email;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext_email_test.xml")
public class MailSupporterTest extends AbstractJUnit4SpringContextTests {

	@Resource(name = "mailSupporter")
	private IMailSupporter mailSupporter;
	
	@Resource(name = "mail")
	private Mail mail;
	
	@Test
	public void testSendMail() {
		try {
			mailSupporter.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
