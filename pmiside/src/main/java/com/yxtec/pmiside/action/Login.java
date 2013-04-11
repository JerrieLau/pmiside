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
 * 登入
 * @author Jerrie
 * 
 */
@Component("loginaction")
@Scope("prototype")
public class Login extends ActionSupport {

	private static final long serialVersionUID = 6505402693121371494L;

	private transient IUserService userService;

	private User user;

	@Resource(name = "userService")
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
		clearErrorsAndMessages();
		User pUser = userService.findUser(user);
		if (pUser == null) {
			ServletActionContext.getRequest().setAttribute("actionerror", getText("ui.login.fail"));
			return INPUT;
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("user", pUser);
			return SUCCESS;
		}
	}
}
