package com.css.pos.view.category;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dto.category.CategoryDto;
import com.css.pos.service.category.CategoryService;
import com.css.pos.view.common.BaseBean;

@ManagedBean
@ViewScoped
@Component
public class CategoryView extends BaseBean{
	
	private List<CategoryDto> allCats; 
	
	private CategoryDto cat = new CategoryDto();
	private boolean updateMode;
	@Autowired
	private CategoryService catService;
	
	@PostConstruct
	public void inti() {
		cat = new CategoryDto();
		allCats = new ArrayList<>();
		fillInBusinessLinesList();
	}
	public void handleLogoUpload(FileUploadEvent event) {
		// TODO Auto-generated method stub
		try{
			String dbLogo = super.handleLogoUpload(event, "category.logo.upload_folder");
			cat.setLogo(dbLogo);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String prepareEdit() {
		try {
			updateMode = true;
			RequestContext ctxt = RequestContext.getCurrentInstance();
			ctxt.execute("PF('createCat_dlg').show();");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String delete() {
		try {
			if (catService.delete(cat.getId()) > 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Category removed Successfuly :)"));
				allCats.remove(cat);
				cat = new CategoryDto();
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
	public String loadCategories() {
		return null;
	}
	private UploadedFile file;
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public List<CategoryDto> getAllCats() {
		return allCats;
	}
	public void setAllCats(List<CategoryDto> allCats) {
		this.allCats = allCats;
	}
	public void prepare4Add(ActionEvent e) {
		cat = new CategoryDto();
		RequestContext ctxt = RequestContext.getCurrentInstance();
		ctxt.execute("PF('createCat_dlg').show();");
	}
	public CategoryDto getCat() {
		return cat;
	}
	public void setCat(CategoryDto cat) {
		this.cat = cat;
	}
	public boolean isUpdateMode() {
		return updateMode;
	}
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
	public CategoryService getCatService() {
		return catService;
	}
	public void setCatService(CategoryService catService) {
		this.catService = catService;
	}
	
	public String saveCategory() {
		try {
			cat.setCompanyId(getSelectedCompany());
			int result  = catService.save(cat);
			if(result > 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Category Saved Successfuly :)"));
				if(getCompanies() == null) setCompanies( new ArrayList<>());
				if(!updateMode) {
					if(allCats==null) allCats = new ArrayList<>();
					allCats.add(cat);
				}
				cat = new CategoryDto();
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
	
	public void loadCompanyCats() {
		allCats = catService.list(getSelectedCompany());
	}
	
}
