package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COMPANY database table.
 * 
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String address;

	private String logo;

	private String mobile;

	private String name;

	private String phone;

	public Company(String id) {
		this.id = id;
	}
	//bi-directional many-to-one association to BusinessLine
	@ManyToOne
	@JoinColumn(name="BUSINESS")
	private BusinessLine businessLine;

	
	public Company() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	
	public BusinessLine getBusinessLine() {
		return this.businessLine;
	}

	public void setBusinessLine(BusinessLine businessLine) {
		this.businessLine = businessLine;
	}

	

}