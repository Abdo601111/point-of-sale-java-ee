package com.css.pos.dto.customer;

public class SupplierDto {
	private Integer id;
	private String name, code, email, phone, mobile, companyId, address;
	private Integer country, state, city;
	
	public Integer getCountry() {
		return country;
	}
	public void setCountry(Integer country) {
		this.country = country;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public SupplierDto() {
		super();
	}
	public SupplierDto copy() {
		SupplierDto newS = new SupplierDto();
		newS.setId(this.id);
		newS.setCountry(this.getCountry());
		newS.setCity(this.getCity());
		newS.setState(this.getState());
		newS.setAddress(this.getAddress());
		newS.setCode(this.getCode());
		newS.setCompanyId(this.getCompanyId());
		newS.setEmail(this.getEmail());
		newS.setMobile(this.getMobile());
		newS.setName(this.getName());
		newS.setPhone(this.getPhone());
		
		return newS;
	}
	public SupplierDto(Integer id) {
		super();
		this.id = id;
	}

	public SupplierDto(String name, String code, String email, String phone, String mobile, String companyId
			, String address, int country, int state, int city) {
		super();
		this.name = name;
		this.code = code;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
		this.companyId = companyId;
		this.address = address;
		this.country = country;
		this.state =state;
		this.city = city;
	}

	public SupplierDto(Integer id, String name, String code, String email, String phone, String mobile,
			String companyId, String address, int country, int state, int city) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
		this.companyId = companyId;
		this.address = address;
		this.country = country;
		this.state =state;
		this.city = city;
	}

	

	@Override
	public String toString() {
		return "SupplierDto [id=" + id + ", name=" + name + ", code=" + code + ", email=" + email + ", phone=" + phone
				+ ", mobile=" + mobile + ", companyId=" + companyId + ", address=" + address + ", country=" + country
				+ ", state=" + state + ", city=" + city + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + city;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + country;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + state;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupplierDto other = (SupplierDto) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city != other.city)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (country != other.country)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (state != other.state)
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
