package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ROLES" database table.
 * 
 */
@Entity
@Table(name="\"ROLES\"")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	public Role(String id) {
		this.id=id;
	}
	@Id
	private String id;

	private String name;

	//bi-directional many-to-one association to People
	@OneToMany(mappedBy="roleBean")
	private List<People> peoples;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY")
	private Company companyBean;

	//bi-directional many-to-one association to RolePermission
	@OneToMany(mappedBy="roleBean")
	private List<RolePermission> rolePermissions;

	public Role() {
	}

	public Role(String id, String name, Company companyBean) {
		super();
		this.id = id;
		this.name = name;
		this.companyBean = companyBean;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<People> getPeoples() {
		return this.peoples;
	}

	public void setPeoples(List<People> peoples) {
		this.peoples = peoples;
	}

	public People addPeople(People people) {
		getPeoples().add(people);
		people.setRoleBean(this);

		return people;
	}

	public People removePeople(People people) {
		getPeoples().remove(people);
		people.setRoleBean(null);

		return people;
	}

	public Company getCompanyBean() {
		return this.companyBean;
	}

	public void setCompanyBean(Company companyBean) {
		this.companyBean = companyBean;
	}

	public List<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	public RolePermission addRolePermission(RolePermission rolePermission) {
		getRolePermissions().add(rolePermission);
		rolePermission.setRoleBean(this);

		return rolePermission;
	}

	public RolePermission removeRolePermission(RolePermission rolePermission) {
		getRolePermissions().remove(rolePermission);
		rolePermission.setRoleBean(null);

		return rolePermission;
	}

}