package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PAYMENTS database table.
 * 
 */
@Entity
@Table(name="PAYMENTS")
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="DISCOUNT_PER")
	private String discountPer;

	@Column(name="DISCOUNT_VAL")
	private String discountVal;

	private String payment;

	@Lob
	private byte[] returnmsg;

	private double total;

	private String transid;

	private String writewhy;

//	//bi-directional many-to-one association to InsuranceCompany
//	@ManyToOne
//	@JoinColumn(name="INSURANCE")
//	private InsuranceCompany insuranceCompany;

	//bi-directional many-to-one association to Receipt
	@ManyToOne
	@JoinColumn(name="RECEIPT")
	private Receipt receiptBean;

	public Payment() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDiscountPer() {
		return this.discountPer;
	}

	public void setDiscountPer(String discountPer) {
		this.discountPer = discountPer;
	}

	public String getDiscountVal() {
		return this.discountVal;
	}

	public void setDiscountVal(String discountVal) {
		this.discountVal = discountVal;
	}

	public String getPayment() {
		return this.payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public byte[] getReturnmsg() {
		return this.returnmsg;
	}

	public void setReturnmsg(byte[] returnmsg) {
		this.returnmsg = returnmsg;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getTransid() {
		return this.transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getWritewhy() {
		return this.writewhy;
	}

	public void setWritewhy(String writewhy) {
		this.writewhy = writewhy;
	}

//	public InsuranceCompany getInsuranceCompany() {
//		return this.insuranceCompany;
//	}
//
//	public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
//		this.insuranceCompany = insuranceCompany;
//	}

	public Receipt getReceiptBean() {
		return this.receiptBean;
	}

	public void setReceiptBean(Receipt receiptBean) {
		this.receiptBean = receiptBean;
	}

}