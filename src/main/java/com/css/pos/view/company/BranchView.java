package com.css.pos.view.company;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dto.company.BranchDto;
import com.css.pos.dto.company.BusinessLineDto;
import com.css.pos.dto.company.CompanyDto;
import com.css.pos.service.company.BranchService;
import com.css.pos.service.company.BusinessLineService;
import com.css.pos.service.company.CompanyService;
import com.css.pos.view.common.BaseBean;

@ManagedBean
@ViewScoped
@Component
public class BranchView extends BaseBean{
	private List<BranchDto> allBranches; 
	private BranchDto branch = new BranchDto();
	private boolean updateMode;
	@Autowired
	private BranchService branchService;
	
	@PostConstruct
	public void inti() {
		 branch = new BranchDto();
		allBranches = new ArrayList<BranchDto>();
		fillInBusinessLinesList();
	}
	public void loadBusinessLineCompanies() {
		setCompanies(getCompanyServices().list(getSelectedBLine()));
	}
	public String saveBranch() {
		try {
			int result  = branchService.save(branch);
			if(result > 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Branch Saved Successfuly :)"));
				if(getCompanies() == null) setCompanies(new ArrayList<>());
				if(!updateMode) {
					if(allBranches==null) allBranches = new ArrayList<>();
					allBranches.add(branch);
				}
				branch = new BranchDto();
				updateMode = false;
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error","Something went wrong please try again"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
			e.printStackTrace();
		}
		
		return null;
	}
	public String loadBranches() {
		try {
			allBranches = branchService.list(getSelectedCompany());
		}catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
		}
		return null;
	}
	public String prepareEdit() {
		try {
			updateMode = true;
			RequestContext ctxt = RequestContext.getCurrentInstance();
			ctxt.execute("PF('createBr_dlg').show();");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String delete() {
		try {
			if (branchService.delete(branch.getId()) > 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Company branch removed Successfuly :)"));
				allBranches.remove(branch);
				branch = new BranchDto();
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
						"Error", "Something went wrong please try again"));
			}
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
		}
		return null;
	}
	public List<BranchDto> getAllBranches() {
		return allBranches;
	}
	public void setAllBranches(List<BranchDto> allBranches) {
		this.allBranches = allBranches;
	}
	
	public BranchDto getBranch() {
		return branch;
	}
	public void setBranch(BranchDto branch) {
		this.branch = branch;
	}
	public boolean isUpdateMode() {
		return updateMode;
	}
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
	public BranchService getBranchService() {
		return branchService;
	}
	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}
}
