package com.css.pos.dto.invoices;

import java.util.Date;
import java.util.List;
import java.util.UUID;



public class InvoiceDto {
	private String id ;
	private float discountTotal;
	private Integer status;
	private float totalCost;
	private float total;
	private float subTotal;
	private Date invDate;
	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	private float discount;
	private float cashPayment, debitPayment, creditPayment;
	public float getCashPayment() {
		return cashPayment;
	}

	public void setCashPayment(float cashPayment) {
		this.cashPayment = cashPayment;
	}

	public float getDebitPayment() {
		return debitPayment;
	}

	public void setDebitPayment(float debitPayment) {
		this.debitPayment = debitPayment;
	}

	public float getCreditPayment() {
		return creditPayment;
	}

	public void setCreditPayment(float creditPayment) {
		this.creditPayment = creditPayment;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	private Integer supplier;
	private Integer suppInvoiceNumber;
	public Integer getSuppInvoiceNumber() {
		return suppInvoiceNumber;
	}

	public void setSuppInvoiceNumber(Integer suppInvoiceNumber) {
		this.suppInvoiceNumber = suppInvoiceNumber;
	}

	public Integer getSupplier() {
		return supplier;
	}

	public void setSupplier(Integer supplier) {
		this.supplier = supplier;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public InvoiceDto() {super();}

	public InvoiceDto(String id, float discountTotal, Integer status, float totalCost, float total,
			Integer number, Integer type, List<InvoiceItemDto> items, String branch, String customer, String casher,
			String receipt) {
		super();
		this.id = id;
		this.discountTotal = discountTotal;
		this.status = status;
		this.totalCost = totalCost;
		this.total = total;
		this.number = number;
		this.type = type;
		this.items = items;
		this.branch = branch;
		this.customer = customer;
		this.casher = casher;
		this.receipt = receipt;
	}

	public float getDiscountTotal() {
		return discountTotal;
	}

	public void setDiscountTotal(float discountTotal) {
		this.discountTotal = discountTotal;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<InvoiceItemDto> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItemDto> items) {
		this.items = items;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCasher() {
		return casher;
	}

	public void setCasher(String casher) {
		this.casher = casher;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	/**
	 * Invoice number seen by the customer/supplier 
	 * mapped to the ticket id
	 */
	private Integer number; 
	
	private Integer type;
	private List<InvoiceItemDto> items;

	private String branch;

	private String customer;
	private String casher;

	private String receipt; 
}
