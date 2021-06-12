package com.css.pos.view.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.css.pos.dto.category.CategoryDto;
import com.css.pos.dto.company.CompanyDto;
import com.css.pos.dto.security.RoleDto;
import com.css.pos.service.security.RoleService;
import com.css.pos.view.common.BaseBean;

@ManagedBean
@ViewScoped
@Component
public class RoleView extends BaseBean{
	@Autowired
	private RoleService roleService;
	private List<RoleDto> allRoles;
	private RoleDto role;
	
	
	@PostConstruct
	public void inti() {
		role = new RoleDto();
		allRoles = new ArrayList<>();
		fillInBusinessLinesList();
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public List<RoleDto> getAllRoles() {
		return allRoles;
	}
	public void setAllRoles(List<RoleDto> allRoles) {
		this.allRoles = allRoles;
	}
	public RoleDto getRole() {
		return role;
	}
	public void setRole(RoleDto role) {
		this.role = role;
	}
	
	
	public void loadRoles() {
		try {
			allRoles = roleService.list(getSelectedCompany());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	public void addNewRole(ActionEvent e) {
		if(allRoles == null) allRoles = new ArrayList<>();
		allRoles.add(new RoleDto());
	}
	public String save() {
		 try {
			 role.setCompanyId(getSelectedCompany());
			 if(roleService.save(role)>0) {
				 FacesContext.getCurrentInstance().addMessage(null, 
						 new FacesMessage(FacesMessage.SEVERITY_INFO, "Role saved successfuly", "Role saved successfuly"));
			 }else {
				 FacesContext.getCurrentInstance().addMessage(null, 
						 new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Occured try again later", "Error Occured try again later"));
			 }
		 }catch (Exception e2) {
			e2.printStackTrace();
		}
		 return null;
	}
	public String delete() {
		try {
			 if(roleService.delete(role.getId())>0) {
				 allRoles.remove(role);
				 FacesContext.getCurrentInstance().addMessage(null, 
						 new FacesMessage(FacesMessage.SEVERITY_INFO, "Role deleted successfuly", ""));
			 }else {
				 FacesContext.getCurrentInstance().addMessage(null, 
						 new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Occured try again later", ""));
			 }
		 }catch (Exception e2) {
			e2.printStackTrace();
		}
		return null;
	}
}
