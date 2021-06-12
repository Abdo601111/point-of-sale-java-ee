package com.css.pos.dto.payment;



public class PaymentDto {
	
	private String id;

	private String discountPer;

	private String discountVal;
	private String payment;
	private double total;

	private String transid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(String discountPer) {
		this.discountPer = discountPer;
	}

	public String getDiscountVal() {
		return discountVal;
	}

	public void setDiscountVal(String discountVal) {
		this.discountVal = discountVal;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getWritewhy() {
		return writewhy;
	}

	public void setWritewhy(String writewhy) {
		this.writewhy = writewhy;
	}

	public String getReceiptID() {
		return receiptID;
	}

	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
	}

	private String writewhy;

	private String receiptID;


}
