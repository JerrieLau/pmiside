/**
 * 
 */
package com.yxtec.pmiside.action;


import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.opensymphony.xwork2.ActionSupport;
import com.yxtec.pmiside.mode.User;
import com.yxtec.pmiside.service.IUserService;

/**
 * @author Jerrie
 * 
 */
@Component("registerAction")
@Scope("prototype")
public class Register extends ActionSupport {

	private static final long serialVersionUID = -7041431778886814834L;

	private transient IUserService userService;

	private User user;

	public IUserService getUserService() {
		return userService;
	}

	@Resource(name="userService")
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() {
		User pUser = userService.findByUserId(user.getUserId());
		if (pUser != null) {
			ServletActionContext.getRequest().setAttribute("actionerror", getText("ui.regist.existed"));
			return INPUT;
		} else {
			userService.save(user);
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return SUCCESS;
		}
	}

}
