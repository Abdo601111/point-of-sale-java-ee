package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the RECEIPTS database table.
 * 
 */
@Entity
@Table(name="RECEIPTS")
@NamedQuery(name="Receipt.findAll", query="SELECT r FROM Receipt r")
public class Receipt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Lob
	private byte[] attributes;

	private Timestamp datenew;

	@Column(name="\"MONEY\"")
	private String money;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="receiptBean")
	private List<Payment> payments;

	//bi-directional one-to-one association to Ticket
	@OneToOne(mappedBy="receipt")
	private Ticket ticket;

	public Receipt() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getAttributes() {
		return this.attributes;
	}

	public void setAttributes(byte[] attributes) {
		this.attributes = attributes;
	}

	public Timestamp getDatenew() {
		return this.datenew;
	}

	public void setDatenew(Timestamp datenew) {
		this.datenew = datenew;
	}

	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setReceiptBean(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setReceiptBean(null);

		return payment;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}