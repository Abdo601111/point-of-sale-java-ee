package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the CUSTOMERS database table.
 * 
 */
@Entity
@Table(name="CUSTOMERS")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="ADD_LEFT")
	private Float addLeft;

	@Column(name="ADD_RIGHT")
	private Float addRight;

	private String address;

	private String address2;

	@Column(name="AXIS_LEFT")
	private Float axisLeft;

	@Column(name="AXIS_RIGHT")
	private Float axisRight;

	private String card;

	private Timestamp curdate;

	private Float curdebt;

	@Column(name="CYL_LEFT")
	private Float cylLeft;

	@Column(name="CYL_RIGHT")
	private Float cylRight;

	@Column(name="DIA_LEFT")
	private Float diaLeft;

	@Column(name="DIA_RIGHT")
	private Float diaRight;

	private String email;

	private String fax;

	private String firstname;

	@Column(name="INDEX_LEFT")
	private Float indexLeft;

	@Column(name="INDEX_RIGHT")
	private Float indexRight;

	private String lastname;

	private Float maxdebt;

	private String name;

	private String notes;

	private String phone;

	private String phone2;

	private String postal;

	@Column(name="QTY_LEFT")
	private Float qtyLeft;

	@Column(name="QTY_RIGHT")
	private Float qtyRight;

	private String searchkey;

	@Column(name="SPH_LEFT")
	private Float sphLeft;

	@Column(name="SPH_RIGHT")
	private Float sphRight;

	private String taxcategory;

	private String taxid;

	private Integer visible;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="CITY")
	private City cityBean;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY")
	private Company companyBean;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="COUNTRY")
	private Country countryBean;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="\"STATE\"")
	private State stateBean;

//	//bi-directional many-to-one association to Ticket
//	@OneToMany(mappedBy="customerBean")
//	private List<Ticket> tickets;

	public Customer() {
	}
	public Customer(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Float getAddLeft() {
		return this.addLeft;
	}

	public void setAddLeft(Float addLeft) {
		this.addLeft = addLeft;
	}

	public Float getAddRight() {
		return this.addRight;
	}

	public void setAddRight(Float addRight) {
		this.addRight = addRight;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Float getAxisLeft() {
		return this.axisLeft;
	}

	public void setAxisLeft(Float axisLeft) {
		this.axisLeft = axisLeft;
	}

	public Float getAxisRight() {
		return this.axisRight;
	}

	public void setAxisRight(Float axisRight) {
		this.axisRight = axisRight;
	}

	public String getCard() {
		return this.card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public Timestamp getCurdate() {
		return this.curdate;
	}

	public void setCurdate(Timestamp curdate) {
		this.curdate = curdate;
	}

	public Float getCurdebt() {
		return this.curdebt;
	}

	public void setCurdebt(Float curdebt) {
		this.curdebt = curdebt;
	}

	public Float getCylLeft() {
		return this.cylLeft;
	}

	public void setCylLeft(Float cylLeft) {
		this.cylLeft = cylLeft;
	}

	public Float getCylRight() {
		return this.cylRight;
	}

	public void setCylRight(Float cylRight) {
		this.cylRight = cylRight;
	}

	public Float getDiaLeft() {
		return this.diaLeft;
	}

	public void setDiaLeft(Float diaLeft) {
		this.diaLeft = diaLeft;
	}

	public Float getDiaRight() {
		return this.diaRight;
	}

	public void setDiaRight(Float diaRight) {
		this.diaRight = diaRight;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Float getIndexLeft() {
		return this.indexLeft;
	}

	public void setIndexLeft(Float indexLeft) {
		this.indexLeft = indexLeft;
	}

	public Float getIndexRight() {
		return this.indexRight;
	}

	public void setIndexRight(Float indexRight) {
		this.indexRight = indexRight;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Float getMaxdebt() {
		return this.maxdebt;
	}

	public void setMaxdebt(Float maxdebt) {
		this.maxdebt = maxdebt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public Float getQtyLeft() {
		return this.qtyLeft;
	}

	public void setQtyLeft(Float qtyLeft) {
		this.qtyLeft = qtyLeft;
	}

	public Float getQtyRight() {
		return this.qtyRight;
	}

	public void setQtyRight(Float qtyRight) {
		this.qtyRight = qtyRight;
	}

	public String getSearchkey() {
		return this.searchkey;
	}

	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}

	public Float getSphLeft() {
		return this.sphLeft;
	}

	public void setSphLeft(Float sphLeft) {
		this.sphLeft = sphLeft;
	}

	public Float getSphRight() {
		return this.sphRight;
	}

	public void setSphRight(Float sphRight) {
		this.sphRight = sphRight;
	}

	public String getTaxcategory() {
		return this.taxcategory;
	}

	public void setTaxcategory(String taxcategory) {
		this.taxcategory = taxcategory;
	}

	public String getTaxid() {
		return this.taxid;
	}

	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}

	public Integer getVisible() {
		return this.visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public City getCityBean() {
		return this.cityBean;
	}

	public void setCityBean(City cityBean) {
		this.cityBean = cityBean;
	}

	public Company getCompanyBean() {
		return this.companyBean;
	}

	public void setCompanyBean(Company companyBean) {
		this.companyBean = companyBean;
	}

	public Country getCountryBean() {
		return this.countryBean;
	}

	public void setCountryBean(Country countryBean) {
		this.countryBean = countryBean;
	}

	public State getStateBean() {
		return this.stateBean;
	}

	public void setStateBean(State stateBean) {
		this.stateBean = stateBean;
	}

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
//		ticket.setCustomerBean(this);
//
//		return ticket;
//	}
//
//	public Ticket removeTicket(Ticket ticket) {
//		getTickets().remove(ticket);
//		ticket.setCustomerBean(null);
//
//		return ticket;
//	}

}