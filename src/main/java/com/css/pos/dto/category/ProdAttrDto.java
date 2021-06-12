package com.css.pos.dto.category;

public class ProdAttrDto {
	public ProdAttrDto() {
		super();
	}
	private String id;
	public ProdAttrDto(String id, String productId, String attributeId, String value) {
		super();
		this.id = id;
		this.productId = productId;
		this.attributeId = attributeId;
		this.value = value;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String productId;
	private String attributeId;
	private String value;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributeId == null) ? 0 : attributeId.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ProdAttrDto other = (ProdAttrDto) obj;
		if (attributeId == null) {
			if (other.attributeId != null)
				return false;
		} else if (!attributeId.equals(other.attributeId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	public String getProductId() {
		return productId;
	}
	public ProdAttrDto(String productId, String attributeId, String value) {
		super();
		this.productId = productId;
		this.attributeId = attributeId;
		this.value = value;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
