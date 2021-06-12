package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PEOPLE database table.
 * 
 */
@Entity
@NamedQuery(name="People.findAll", query="SELECT p FROM People p")
public class People implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.DATE)
	@Column(name="ACTIVE_UNTIL")
	private Date activeUntil;

	private String address;

	private String apppassword;

	private String card;

	private String email;

	private String image;

	@Column(name="IS_COMPANY_ADMIN")
	private Integer isCompanyAdmin;

	@Column(name="IS_SUPER_ADMIN")
	private Integer isSuperAdmin;

	@Column(name="LOGIN_NAME")
	private String loginName;

	private String mobile;

	private String name;

	private String phone;

	private Integer visible;
	
	//bi-directional many-to-one association to ClosedCash
	@OneToMany  //means one user has set of closed cashes
	@JoinColumn(name="CLOSEDCASH")
	private List<Closedcash> moneyBean;
	
	public List<Closedcash> getMoneyBean() {
		return moneyBean;
	}
	public void setMoneyBean(List<Closedcash> moneyBean) {
		this.moneyBean = moneyBean;
	}

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY")
	private Company companyBean;
	
	
	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="\"ROLE\"")
	private Role roleBean;

	//bi-directional many-to-one association to PeopleBranch
//	@OneToMany(mappedBy="peopleBean")
//	private List<PeopleBranch> peopleBranches;
//
//	//bi-directional many-to-one association to Ticket
//	@OneToMany(mappedBy="people")
//	private List<Ticket> tickets;

	public People() {
	}
	public People(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getActiveUntil() {
		return this.activeUntil;
	}

	public void setActiveUntil(Date activeUntil) {
		this.activeUntil = activeUntil;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApppassword() {
		return this.apppassword;
	}

	public void setApppassword(String apppassword) {
		this.apppassword = apppassword;
	}

	public String getCard() {
		return this.card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getIsCompanyAdmin() {
		return this.isCompanyAdmin;
	}

	public void setIsCompanyAdmin(Integer isCompanyAdmin) {
		this.isCompanyAdmin = isCompanyAdmin;
	}

	public Integer getIsSuperAdmin() {
		return this.isSuperAdmin;
	}

	public void setIsSuperAdmin(Integer isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public Integer getVisible() {
		return this.visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public Company getCompanyBean() {
		return this.companyBean;
	}

	public void setCompanyBean(Company companyBean) {
		this.companyBean = companyBean;
	}

	public Role getRoleBean() {
		return this.roleBean;
	}

	public void setRoleBean(Role roleBean) {
		this.roleBean = roleBean;
	}

//	public List<PeopleBranch> getPeopleBranches() {
//		return this.peopleBranches;
//	}
//
//	public void setPeopleBranches(List<PeopleBranch> peopleBranches) {
//		this.peopleBranches = peopleBranches;
//	}
//
//	public PeopleBranch addPeopleBranch(PeopleBranch peopleBranch) {
//		getPeopleBranches().add(peopleBranch);
//		peopleBranch.setPeopleBean(this);
//
//		return peopleBranch;
//	}
//
//	public PeopleBranch removePeopleBranch(PeopleBranch peopleBranch) {
//		getPeopleBranches().remove(peopleBranch);
//		peopleBranch.setPeopleBean(null);
//
//		return peopleBranch;
//	}
//
//	public List<Ticket> getTickets() {
//		return this.tickets;
//	}
//
//	public void setTickets(List<Ticket> tickets) {
//		this.tickets = tickets;
//	}
//
//	public Ticket addTicket(Ticket ticket) {
//		getTickets().add(ticket);
//		ticket.setPeople(this);
//
//		return ticket;
//	}
//
//	public Ticket removeTicket(Ticket ticket) {
//		getTickets().remove(ticket);
//		ticket.setPeople(null);
//
//		return ticket;
//	}

}