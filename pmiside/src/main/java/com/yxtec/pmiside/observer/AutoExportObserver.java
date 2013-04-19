/**
 * 
 */
package com.yxtec.pmiside.observer;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yxtec.pmiside.domain.email.IMailSupporter;
import com.yxtec.pmiside.domain.email.Mail;


/**
 * 自动导出服务观察者
 * @author Jerrie
 *
 */
@Aspect
@Component("autoExportObserver")
public class AutoExportObserver {

	@Resource(name = "mailSupporter")
	private IMailSupporter mailSupporter;
	
	@AfterThrowing(pointcut="execution(* com.yxtec.pmiside.service.AutoExportServiceImpl.export(..))", throwing="ex")
	public void notificationAutoExportFail(Exception ex) {
		// 发送邮件
		try {
			Mail mail = new Mail();
			mail.setFrom(mailSupporter.getUsername());
			mail.setTo("jerrielau@qq.com");
			mail.setSubject("PMISIDE站点自动导出服务出现问题！");
			mail.setContent(ex.toString());
			mailSupporter.sendMail(mail);
		} catch (Exception e) {
			LoggerFactory.getLogger(AutoExportObserver.class).error("自动导出服务观察到异常，向管理员发送邮件失败！", e);
		}
	}
}
