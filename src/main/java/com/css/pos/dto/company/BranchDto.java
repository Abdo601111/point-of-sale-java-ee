package com.css.pos.dto.company;

public class BranchDto {
	private String id;
	private String address;
	private String mobile;
	private String name;
	private String phone;
	
	//foreign key
	private String companyId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public BranchDto() {}
	public BranchDto(String id, String address, String mobile, String name, String phone, String companyId) {
		super();
		this.id = id;
		this.address = address;
		this.mobile = mobile;
		this.name = name;
		this.phone = phone;
		this.companyId = companyId;
	}
}
