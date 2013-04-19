/**
 * 
 */
package com.yxtec.pmiside.observer;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.yxtec.pmiside.mode.PMISExportUserMessage;
import com.yxtec.pmiside.mode.User;
import com.yxtec.pmiside.security.SecurityUtilities;

/**
 * @author Jerrie
 * 
 */
@Aspect
@Component("mwexportObserver")
public class MWorkExproterActionObserver {

	@Resource(name = "securityUtilities")
	private SecurityUtilities su;

	@Before("execution(* com.yxtec.pmiside.service.ExportServiceImpl.savePMISExportUserMessage(..)) && args(message)")
	public void notifyEncPmisPassword(PMISExportUserMessage message) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (!message.getPmisPassword().isEmpty()) {
			message.setPmisPassword(su.encrypt(message.getPmisPassword(), user.getUserId()));
		}
	}

	@After("execution(* com.yxtec.pmiside.service.ExportServiceImpl.savePMISExportUserMessage(..)) && args(message)")
	public void notifyDecPmisPassword(PMISExportUserMessage message) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (!message.getPmisPassword().isEmpty()) {
			message.setPmisPassword(su.decrypt(message.getPmisPassword(), user.getUserId()));
		}
	}

	@AfterReturning("execution(* com.yxtec.pmiside.action.MWorkExporter.preExport(..))")
	public void modifyRspPassword() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		PMISExportUserMessage message = (PMISExportUserMessage) ServletActionContext.getRequest().getAttribute("msg");
		if (!message.getPmisPassword().isEmpty()) {
			message.setPmisPassword(su.decrypt(message.getPmisPassword(), user.getUserId()));
		}
	}
}
