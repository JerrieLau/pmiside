package com.yxtec.pmiside.security;

import static org.junit.Assert.*;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext_security_test.xml")
public class SecurityUtilitiesTest {

	@Resource(name="su")
	private SecurityUtilities su;
	
	@Test
	public void testEncrypt() {
		try {
			String expected = null;
			String actual = null;
			
			expected = "d5265314e5f8102caf4f20989492a92f";
			actual = su.encrypt("J123456", "jerrietm@gmail.com");
			assertEquals(expected, actual);
			
			expected = "cc0bec686c8ae0dce0823dbeaf8e2540";
			actual = su.encrypt("J123456", "中文密钥");
			assertEquals(expected, actual);
			
			
			expected = "201c5add2520712f3a17cd850eb63c26";
			actual = su.encrypt("中文明文", "中文密钥");
			assertEquals(expected, actual);
			
//			LoggerFactory.getLogger(SecurityUtilities.class).info(actual);
		} catch (Exception e) {
			fail();
			LoggerFactory.getLogger(SecurityUtilities.class).error("", e);
		}
	}

	@Test
	public void testDecrypt() {
		try {
			String expected = null;
			String actual = null;
			
			expected = "J123456";
			actual = su.decrypt("d5265314e5f8102caf4f20989492a92f", "jerrietm@gmail.com");
			assertEquals(expected, actual);
			
			expected = "J123456";
			actual = su.decrypt("cc0bec686c8ae0dce0823dbeaf8e2540", "中文密钥");
			assertEquals(expected, actual);
			
			expected = "中文明文";
			actual = su.decrypt("201c5add2520712f3a17cd850eb63c26", "中文密钥");
			assertEquals(expected, actual);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
