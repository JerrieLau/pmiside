/**
 * 
 */
package com.yxtec.pmiside.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @author Jerrie
 *
 */
@Component("authorityInterceptor")
public class AuthorityIntercepter extends MethodFilterInterceptor {

	private static final long serialVersionUID = 4498591864944997179L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		if(invocation.getProxy().getActionName().equals("register")){
			return invocation.invoke();
		}
		if(invocation.getProxy().getActionName().equals("login")){
			return invocation.invoke();
		}
		
		if(ServletActionContext.getRequest().getRequestURI().endsWith("login.jsp")) {
			return invocation.invoke();
		}
		
		Object userid = ServletActionContext.getRequest().getSession().getAttribute("user");
		if(userid != null) {
			return invocation.invoke();
		}
		return Action.LOGIN;
	}
}
