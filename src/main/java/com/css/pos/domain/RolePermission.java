package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ROLE_PERMISSIONS database table.
 * 
 */
@Entity
@Table(name="ROLE_PERMISSIONS")
@NamedQuery(name="RolePermission.findAll", query="SELECT r FROM RolePermission r")
public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="\"ROLE\"")
	private Role roleBean;

	//bi-directional many-to-one association to Screen
	@ManyToOne
	@JoinColumn(name="\"SCREEN\"")
	private Screen screenBean;

	public RolePermission() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Role getRoleBean() {
		return this.roleBean;
	}

	public void setRoleBean(Role roleBean) {
		this.roleBean = roleBean;
	}

	public Screen getScreenBean() {
		return this.screenBean;
	}

	public void setScreenBean(Screen screenBean) {
		this.screenBean = screenBean;
	}

}