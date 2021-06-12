package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the "SCREEN" database table.
 * 
 */
@Entity
@Table(name="\"SCREEN\"")
@NamedQuery(name="Screen.findAll", query="SELECT s FROM Screen s")
public class Screen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private BigDecimal isroot;

	private String name;

	@Column(name="NAME_AR")
	private String nameAr;

	private String parent;

	@Column(name="SCR_NUMBER")
	private BigDecimal scrNumber;

	private String url;

	//bi-directional many-to-one association to RolePermission
	@OneToMany(mappedBy="screenBean")
	private List<RolePermission> rolePermissions;

	public Screen() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getIsroot() {
		return this.isroot;
	}

	public void setIsroot(BigDecimal isroot) {
		this.isroot = isroot;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAr() {
		return this.nameAr;
	}

	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public BigDecimal getScrNumber() {
		return this.scrNumber;
	}

	public void setScrNumber(BigDecimal scrNumber) {
		this.scrNumber = scrNumber;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	public RolePermission addRolePermission(RolePermission rolePermission) {
		getRolePermissions().add(rolePermission);
		rolePermission.setScreenBean(this);

		return rolePermission;
	}

	public RolePermission removeRolePermission(RolePermission rolePermission) {
		getRolePermissions().remove(rolePermission);
		rolePermission.setScreenBean(null);

		return rolePermission;
	}

}