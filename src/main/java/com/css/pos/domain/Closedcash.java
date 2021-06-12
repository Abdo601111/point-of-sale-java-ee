package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CLOSEDCASH database table.
 * 
 */
@Entity
@NamedQuery(name="Closedcash.findAll", query="SELECT c FROM Closedcash c")
public class Closedcash implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"MONEY\"")
	private String money;

	private Timestamp dateend;

	private Timestamp datestart;

	@Column(name="\"HOST\"")
	private String host;

	private Integer hostsequence;

	//bi-directional many-to-one association to Branch
	@ManyToOne
	@JoinColumn(name="CASHER")  
	private People casherBean;
		
	public People getCasherBean() {
		return casherBean;
	}

	public void setCasherBean(People casherBean) {
		this.casherBean = casherBean;
	}

	//bi-directional many-to-one association to Branch
	@ManyToOne
	@JoinColumn(name="BRANCH")
	private Branch branchBean;

	public Closedcash() {
	}

	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public Timestamp getDateend() {
		return this.dateend;
	}

	public void setDateend(Timestamp dateend) {
		this.dateend = dateend;
	}

	public Timestamp getDatestart() {
		return this.datestart;
	}

	public void setDatestart(Timestamp datestart) {
		this.datestart = datestart;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getHostsequence() {
		return this.hostsequence;
	}

	public void setHostsequence(Integer hostsequence) {
		this.hostsequence = hostsequence;
	}

	public Branch getBranchBean() {
		return this.branchBean;
	}

	public void setBranchBean(Branch branchBean) {
		this.branchBean = branchBean;
	}

}