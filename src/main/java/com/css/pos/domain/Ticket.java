package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TICKETS database table.
 * 
 */
@Entity
@Table(name="TICKETS")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private double discount;

	private Integer status;

	@Column(name="TICKET_COST")
	private double ticketCost;

	@Temporal(TemporalType.DATE)
	@Column(name="TICKET_DATE")
	private Date ticketDate;

	@Column(name="TICKET_NUMBER")
	private Integer ticketNumber;

	@Column(name="TICKET_TOTAL")
	private double ticketTotal;

	private Integer ticketid;

	private Integer tickettype;

	//bi-directional many-to-one association to Ticketline
	@OneToMany(mappedBy="ticketBean")
	private List<Ticketline> ticketlines;

	//bi-directional many-to-one association to Branch
	@ManyToOne
	@JoinColumn(name="BRANCH")
	private Branch branchBean;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CUSTOMER")
	private Customer customerBean;

	//bi-directional many-to-one association to People
	@ManyToOne
	@JoinColumn(name="PERSON")
	private People people;

	//bi-directional one-to-one association to Receipt
	@OneToOne
	@JoinColumn(name="ID")
	private Receipt receipt;

	public Ticket() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public double getTicketCost() {
		return this.ticketCost;
	}

	public void setTicketCost(double ticketCost) {
		this.ticketCost = ticketCost;
	}

	public Date getTicketDate() {
		return this.ticketDate;
	}

	public void setTicketDate(Date ticketDate) {
		this.ticketDate = ticketDate;
	}

	public Integer getTicketNumber() {
		return this.ticketNumber;
	}

	public void setTicketNumber(Integer ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public double getTicketTotal() {
		return this.ticketTotal;
	}

	public void setTicketTotal(double ticketTotal) {
		this.ticketTotal = ticketTotal;
	}

	public Integer getTicketid() {
		return this.ticketid;
	}

	public void setTicketid(Integer ticketid) {
		this.ticketid = ticketid;
	}

	public Integer getTickettype() {
		return this.tickettype;
	}

	public void setTickettype(Integer tickettype) {
		this.tickettype = tickettype;
	}

	public List<Ticketline> getTicketlines() {
		return this.ticketlines;
	}

	public void setTicketlines(List<Ticketline> ticketlines) {
		this.ticketlines = ticketlines;
	}

	public Ticketline addTicketline(Ticketline ticketline) {
		getTicketlines().add(ticketline);
		ticketline.setTicketBean(this);

		return ticketline;
	}

	public Ticketline removeTicketline(Ticketline ticketline) {
		getTicketlines().remove(ticketline);
		ticketline.setTicketBean(null);

		return ticketline;
	}

	public Branch getBranchBean() {
		return this.branchBean;
	}

	public void setBranchBean(Branch branchBean) {
		this.branchBean = branchBean;
	}

	public Customer getCustomerBean() {
		return this.customerBean;
	}

	public void setCustomerBean(Customer customerBean) {
		this.customerBean = customerBean;
	}

	public People getPeople() {
		return this.people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public Receipt getReceipt() {
		return this.receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

}