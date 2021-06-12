package com.css.pos.dto.category;

public class CategoryDto {
	private String id;
	private boolean hasProducts;
	private String logo;
	private String name;
	private String parentId;
	private String companyId;
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isHasProducts() {
		return hasProducts;
	}
	public void setHasProducts(boolean hasProducts) {
		this.hasProducts = hasProducts;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public CategoryDto() {
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hasProducts ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logo == null) ? 0 : logo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
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
		CategoryDto other = (CategoryDto) obj;
		if (hasProducts != other.hasProducts)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", hasProducts=" + hasProducts + ", logo=" + logo + ", name=" + name
				+ ", parentId=" + parentId + "]";
	}
	public CategoryDto(String id, boolean hasProducts, String logo, String name, String parentId) {
		super();
		this.id = id;
		this.hasProducts = hasProducts;
		this.logo = logo;
		this.name = name;
		this.parentId = parentId;
	}
	public CategoryDto(boolean hasProducts, String logo, String name, String parentId) {
		super();
		this.hasProducts = hasProducts;
		this.logo = logo;
		this.name = name;
		this.parentId = parentId;
	}
	 
}
