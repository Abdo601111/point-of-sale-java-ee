package com.css.pos.view.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.common.util.POSUtil;
import com.css.pos.dto.category.CategoryDto;
import com.css.pos.dto.customer.CustomerDto;
import com.css.pos.dto.security.RoleDto;
import com.css.pos.dto.security.UserDetailsDto;
import com.css.pos.service.common.GeneralPurposeService;
import com.css.pos.service.security.RoleService;
import com.css.pos.service.security.UserService;
import com.css.pos.view.common.BaseBean;
import com.sun.corba.se.impl.orbutil.closure.Constant;

@ManagedBean
@ViewScoped
@Component
public class UserView  extends BaseBean{
	//temp code
	public String showBooleanValue() {
		System.out.println("Edit mode:"+updateMode);
		return null;
	}
	//temp code
	@Autowired
	private UserService userBusiness;
	@Autowired
	private GeneralPurposeService genericBusiness;
	public GeneralPurposeService getGenericBusiness() {
		return genericBusiness;
	}
	public void setGenericBusiness(GeneralPurposeService genericBusiness) {
		this.genericBusiness = genericBusiness;
	}
	private int passChoice = -1;
	public int getPassChoice() {
		return passChoice;
	}
	public void setPassChoice(int passChoice) {
		this.passChoice = passChoice;
	}
	private boolean useDefaultPassword, randomPassword, useCustomPassword;
	public boolean isUseCustomPassword() {
		return useCustomPassword;
	}
	public void setUseCustomPassword(boolean useCustomPassword) {
		this.useCustomPassword = useCustomPassword;
	}
	public boolean isUseDefaultPassword() {
		return useDefaultPassword;
	}
	public void setUseDefaultPassword(boolean useDefaultPassword) {
		this.useDefaultPassword = useDefaultPassword;
	}
	public void forceUsingCustomPassword(){
		passChoice = -1;
	}
	public boolean isRandomPassword() {
		return randomPassword;
	}
	public void setRandomPassword(boolean randomPassword) {
		this.randomPassword = randomPassword;
	}
	private UserDetailsDto user;
	private String selectedRole;
	private List<RoleDto> allRoles;
	private boolean updateMode;
	private UploadedFile file;
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	public void resetPassword(ActionEvent event) {
		//
			//Do it later.
			
		//
	}
	public boolean isUpdateMode() {
		return updateMode;
	}
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
	@Autowired
	private RoleService rolesBusiness;
	@PostConstruct
	public void init() {
		fillInBusinessLinesList();
		user = new UserDetailsDto();
	}
	public void checkMailAvailability() {
		try {
			String dbEmailId = " ";
			if(updateMode) {
				for(UserDetailsDto c:allUsers) {
					if(c.getId().equalsIgnoreCase(user.getId())){
						dbEmailId = c.getEmail();
						break;
					}
				}
			}
			if(user.getEmail() != null
			   && !user.getEmail().equals("") 
			   && genericBusiness.isEmailValid(user.getEmail())) {
				if(userBusiness.isUserMailExists(user.getEmail(), dbEmailId)) {
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mail already Exists", "Mail already Exists"));
					if(updateMode)
						user.setEmail(dbEmailId);
					else
						user.setEmail("");
				}
			}else {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Email format", "Invalid Email format"));
				user.setEmail("");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public RoleService getRolesBusiness() {
		return rolesBusiness;
	}
	public void setRolesBusiness(RoleService rolesBusiness) {
		this.rolesBusiness = rolesBusiness;
	}
	public void loadRoleUsers() {
		try {
			allUsers = userBusiness.list(selectedRole);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void loadCompanyRoles() {
		try {
			allRoles = rolesBusiness.list(getSelectedCompany());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<RoleDto> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<RoleDto> allRoles) {
		this.allRoles = allRoles;
	}

	public String getSelectedRole() {
		return selectedRole;
	}
	public String delete() {
		try {
			if(userBusiness.delete(user.getId())>0) {
				allUsers.remove(user);
				
				FacesContext.getCurrentInstance().addMessage(null , 
						new FacesMessage(POSUtil.getLocalizedMessage("global.delete.success", FacesContext.getCurrentInstance()).toString()));
			}else {
				FacesContext.getCurrentInstance().addMessage(null , 
						new FacesMessage(POSUtil.getLocalizedMessage("global.delete.fail",  FacesContext.getCurrentInstance()).toString()));
			}
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return null;
	}
	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}

	private List<UserDetailsDto> allUsers;
	public UserDetailsDto getUser() {
		return user;
	}

	public void setUser(UserDetailsDto user) {
		this.user = user;
	}

	public List<UserDetailsDto> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<UserDetailsDto> allUsers) {
		this.allUsers = allUsers;
	}

	public UserService getUserBusiness() {
		return userBusiness;
	}

	public void setUserBusiness(UserService userBusiness) {
		this.userBusiness = userBusiness;
	}
	public String save() {
		try {
			user.setRoleId(selectedRole);
			user.setCompanyId(getSelectedCompany());
			if(useCustomPassword)
				passChoice = -1;
			int result  = userBusiness.save(user, passChoice);
			if(result > 0) {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(POSUtil.getLocalizedMessage("global.save.success", FacesContext.getCurrentInstance()).toString()));
				if(getCompanies() == null) setCompanies( new ArrayList<>());
				if(!updateMode) {
					if(allUsers==null) allUsers = new ArrayList<>();
					allUsers.add(user);
				}
				user = new UserDetailsDto();
				updateMode = false;
			}else {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_FATAL,"",POSUtil.getLocalizedMessage("global.save.fail", FacesContext.getCurrentInstance()).toString()));
			}
			passChoice = -1;
		}catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
			e.printStackTrace();
		}
		
		return null;
	}
}
