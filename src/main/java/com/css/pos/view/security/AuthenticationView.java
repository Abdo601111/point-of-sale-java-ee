package com.css.pos.view.security;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.common.util.POSUtil;
import com.css.pos.dto.security.UserDetailsDto;
import com.css.pos.service.security.UserService;
import com.css.pos.view.common.BaseBean;

@ManagedBean(name = "authenticationBean")
@ViewScoped
@Component
public class AuthenticationView extends BaseBean{
	@Autowired
	private UserService userService;
	@PostConstruct
	public void init() {
		fillInBusinessLinesList();
	}
	
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private String userName, password;
	private String newPassword;
	public String getNewPassword() {
		return newPassword;
	}
	public void changePassword(javax.faces.event.ActionEvent e) {
		System.out.println("to change Password !!");
		try {
			UserDetailsDto user = (UserDetailsDto)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("current_user");
			System.out.println("session password= "+user.getPassword());
			FacesMessage msg = null;
			if(!user.getPassword().equals(password)) {
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Wrong old password", "");
				
			}else {
				if(userService.changePassword(user.getId(), newPassword)) {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password Changed successfuly", "");
					user.setPassword(newPassword); 
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("current_user",user);
				}else {
					msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Something went wrong try again later !!!", "");
				}
				
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void validatePassword(ComponentSystemEvent event) {
		try {
			UIComponent root = event.getComponent();
			UIInput pass = (UIInput) root.findComponent("new_pass_id");
			UIInput rePass = (UIInput) root.findComponent("re_pass_id");
			System.out.println(pass.getLocalValue().toString());
			System.out.println(rePass.getLocalValue().toString());
			if (pass.getLocalValue() != null && rePass.getLocalValue() != null
					&& (pass.getLocalValue().toString() .equals(rePass.getLocalValue().toString()))) {
				System.out.println("valid data");
				return;
			}else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Password and Re-Password must be the same !!!", "Password and Re-Password must be the same !!!");
				FacesContext.getCurrentInstance().addMessage("re_pass_id", msg);
				FacesContext.getCurrentInstance().renderResponse();
				System.out.println(msg);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public String authenticate() {
		UserDetailsDto currentUser= userService.authenticate(userName, password, getSelectedCompany(),getSelectedBranch());
		try {
			if(currentUser != null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("current_user", currentUser);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("current_bline", getSelectedBLine());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("current_company", getSelectedCompany());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("current_branch", getSelectedBranch());
				return "home";
			}
			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL, POSUtil.getLocalizedMessage("login.page.error", FacesContext.getCurrentInstance()).toString(),POSUtil.getLocalizedMessage("login.page.error", FacesContext.getCurrentInstance()).toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
