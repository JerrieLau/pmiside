/**
 * 
 */
package com.yxtec.pmiside.action;


import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登出
 * @author Jerrie
 *
 */
@Component("logoutaction")
@Scope("prototype")
public class Logout extends ActionSupport {

	private static final long serialVersionUID = 6283847357369768972L;
	
	public String execute() throws Exception {
		ServletActionContext.getRequest().getSession().invalidate();
		return null;
	}
}
