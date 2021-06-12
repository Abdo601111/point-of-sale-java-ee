package com.css.pos.dto.category;

import java.util.List;

import com.css.pos.dto.common.LookupDto;

public class ProductDto {
	public ProductDto() {
		super();
	}
	private String id;
	private String logo, code, codetype;
	private boolean iscom, isscale;
	private String name;
	private String categoryId;
	public ProductDto(String id, String logo, String code, boolean isscale, String name, String categoryId,
			String companyId, float pricebuy, float pricesell, String reference, List<ProdAttrDto> attributes) {
		super();
		this.id = id;
		this.logo = logo;
		this.code = code;
		this.isscale = isscale;
		this.name = name;
		this.categoryId = categoryId;
		this.companyId = companyId;
		this.pricebuy = pricebuy;
		this.pricesell = pricesell;
		this.reference = reference;
		this.attributes = attributes;
	}
	public ProductDto(String name, String code, float price) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.code=code;
		this.pricebuy = price;
	}
	private String companyId;
	@Override
	public String toString() {
		return code + " - " + name + " - " + pricebuy ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((codetype == null) ? 0 : codetype.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (iscom ? 1231 : 1237);
		result = prime * result + (isscale ? 1231 : 1237);
		result = prime * result + ((logo == null) ? 0 : logo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(pricebuy);
		result = prime * result + Float.floatToIntBits(pricesell);
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + Float.floatToIntBits(stockcost);
		result = prime * result + Float.floatToIntBits(stockvolume);
		result = prime * result + ((taxcat == null) ? 0 : taxcat.hashCode());
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
		ProductDto other = (ProductDto) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (codetype == null) {
			if (other.codetype != null)
				return false;
		} else if (!codetype.equals(other.codetype))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (iscom != other.iscom)
			return false;
		if (isscale != other.isscale)
			return false;
		if (logo == null) {
			if (other.logo != null)
				return false;
		} else if (!logo.equals(other.logo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(pricebuy) != Float.floatToIntBits(other.pricebuy))
			return false;
		if (Float.floatToIntBits(pricesell) != Float.floatToIntBits(other.pricesell))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (Float.floatToIntBits(stockcost) != Float.floatToIntBits(other.stockcost))
			return false;
		if (Float.floatToIntBits(stockvolume) != Float.floatToIntBits(other.stockvolume))
			return false;
		if (taxcat == null) {
			if (other.taxcat != null)
				return false;
		} else if (!taxcat.equals(other.taxcat))
			return false;
		return true;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodetype() {
		return codetype;
	}
	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}
	public boolean isIscom() {
		return iscom;
	}
	public void setIscom(boolean iscom) {
		this.iscom = iscom;
	}
	public boolean isIsscale() {
		return isscale;
	}
	public void setIsscale(boolean isscale) {
		this.isscale = isscale;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public float getPricebuy() {
		return pricebuy;
	}
	public void setPricebuy(float pricebuy) {
		this.pricebuy = pricebuy;
	}
	public float getPricesell() {
		return pricesell;
	}
	public void setPricesell(float pricesell) {
		this.pricesell = pricesell;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public float getStockvolume() {
		return stockvolume;
	}
	public void setStockvolume(float stockvolume) {
		this.stockvolume = stockvolume;
	}
	public float getStockcost() {
		return stockcost;
	}
	public void setStockcost(float stockcost) {
		this.stockcost = stockcost;
	}
	
	public List<ProdAttrDto> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<ProdAttrDto> attributes) {
		this.attributes = attributes;
	}
	public String getTaxcat() {
		return taxcat;
	}
	public void setTaxcat(String taxcat) {
		this.taxcat = taxcat;
	}
	private float pricebuy, pricesell;
	private String reference;
	private float stockvolume;
	private float stockcost;
	private List<ProdAttrDto> attributes;
	
	private String taxcat;
	
	
}
