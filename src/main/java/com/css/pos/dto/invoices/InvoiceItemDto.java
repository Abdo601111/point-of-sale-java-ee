package com.css.pos.dto.invoices;

public class InvoiceItemDto {
	private String id;
	private String attributeString;
	private Integer order;
	private float price;
	private String taxid;
	public InvoiceItemDto() {super();}
	public InvoiceItemDto(String id, String attributeString, Integer order, float price, String taxid, float cost,
			float units, float unitsRefund, String producId) {
		super();
		this.id = id;
		this.attributeString = attributeString;
		this.order = order;
		this.price = price;
		this.taxid = taxid;
		this.cost = cost;
		this.units = units;
		this.unitsRefund = unitsRefund;
		this.producId = producId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAttributeString() {
		return attributeString;
	}
	public void setAttributeString(String attributeString) {
		this.attributeString = attributeString;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getTaxid() {
		return taxid;
	}
	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}
	public float getCost() {
		return units * price;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getUnits() {
		return units;
	}
	public void setUnits(float units) {
		this.units = units;
	}
	public float getUnitsRefund() {
		return unitsRefund;
	}
	public void setUnitsRefund(float unitsRefund) {
		this.unitsRefund = unitsRefund;
	}
	public String getProducId() {
		return producId;
	}
	public void setProducId(String producId) {
		this.producId = producId;
	}
	private float cost;
	private float units;
	private float balance;
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	private float unitsRefund;
	private String producId, productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
