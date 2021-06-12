package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;


/**
 * The persistent class for the SUPPLIERS_PAYMENTS database table.
 * 
 */
@Entity
@Table(name="SUPPLIERS_PAYMENTS")
@NamedQuery(name="SuppliersPayment.findAll", query="SELECT s FROM SuppliersPayment s")
public class SuppliersPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="PAYMENT_DATE")
	private Timestamp paymentDate;
	@Column(name="PAYMENT_METHOD")
	private String paymentMethod;
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name="PAYMENT_NOTE")
	private String paymentNote;

	@Column(name="PAYMENT_VALUE")
	private float paymentValue;

	//bi-directional many-to-one association to Supplier
	@ManyToOne
	private Supplier supplier;

	public SuppliersPayment() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentNote() {
		return this.paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

	public float getPaymentValue() {
		return this.paymentValue;
	}

	public void setPaymentValue(float paymentValue) {
		this.paymentValue = paymentValue;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}