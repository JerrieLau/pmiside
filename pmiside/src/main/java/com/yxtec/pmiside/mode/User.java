/**
 * 
 */
package com.yxtec.pmiside.mode;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户信息
 * 
 * @author Jerrie
 * 
 */
@Entity
@Table(name = "tbl_user")
public class User implements Serializable {

	private static final long serialVersionUID = 6288852330432223245L;

	private String userId; // email address

	private String password;

	private PMISExportUserMessage msg;

	@Id
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@OneToOne(mappedBy = "user")
	public PMISExportUserMessage getMsg() {
		return msg;
	}

	public void setMsg(PMISExportUserMessage msg) {
		this.msg = msg;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean equals() {
		return true;
	}

}
