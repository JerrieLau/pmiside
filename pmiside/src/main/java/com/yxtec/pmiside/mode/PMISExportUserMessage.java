/**
 * 
 */
package com.yxtec.pmiside.mode;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * PMIS 导出用户信息
 * 
 * @author Jerrie
 * 
 */
@Entity
@Table(name = "tbl_pmisexportusermsg")
public class PMISExportUserMessage implements Serializable {

	private static final long serialVersionUID = -7263175431614191540L;

	private String pmisUserid = "";

	private String pmisPassword = "";

	private String pmisName = "";

	private String pmisProjectName = "";

	private String pmisProjectRole = "";

	private String pmisOfficePlace = "";
	
	private String supplier = "";

	private int year;

	private int month;

	private User user;
	
	private String subscribed = "";

	@Transient
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Transient
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
	public String getPmisUserid() {
		return pmisUserid;
	}

	public void setPmisUserid(String pmisUserid) {
		this.pmisUserid = pmisUserid;
	}

	public String getPmisPassword() {
		return pmisPassword;
	}

	public void setPmisPassword(String pmisPassword) {
		this.pmisPassword = pmisPassword;
	}

	public String getPmisName() {
		return pmisName;
	}

	public void setPmisName(String pmisName) {
		this.pmisName = pmisName;
	}

	public String getPmisProjectName() {
		return pmisProjectName;
	}

	public void setPmisProjectName(String pmisProjectName) {
		this.pmisProjectName = pmisProjectName;
	}

	public String getPmisProjectRole() {
		return pmisProjectRole;
	}

	public void setPmisProjectRole(String pmisProjectRole) {
		this.pmisProjectRole = pmisProjectRole;
	}
	
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getPmisOfficePlace() {
		return pmisOfficePlace;
	}

	public void setPmisOfficePlace(String pmisOfficePlace) {
		this.pmisOfficePlace = pmisOfficePlace;
	}
	
	public String getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(String subscribed) {
		this.subscribed = subscribed;
	}
	

	@Id
	@OneToOne()
	@JoinColumn(name="userid", referencedColumnName="userId", nullable=false, updatable=true)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
