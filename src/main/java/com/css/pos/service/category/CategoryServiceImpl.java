package com.css.pos.service.category;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.common.util.POSConstants;
import com.css.pos.dal.category.CategoryDal;
import com.css.pos.dto.category.CategoryDto;
@Component
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDal dal;
	public CategoryDal getDal() {
		return dal;
	}

	public void setDal(CategoryDal dal) {
		this.dal = dal;
	}

	@Override
	public int save(CategoryDto cat) {
		if(cat.getId() == null) {
			cat.setId(UUID.randomUUID().toString());
		}
		return dal.save(cat);
	}

	@Override
	public int delete(String cat) {
		// TODO Auto-generated method stub
		return dal.delete(cat);
	}

	@Override
	public CategoryDto find(String id) {
		// TODO Auto-generated method stub
		return dal.find(id);
	}

	@Override
	public List<CategoryDto> list() {
		// TODO Auto-generated method stub
		return dal.list();
	}

	@Override
	public List<CategoryDto> list(String company) {
		System.out.println("from cat service");
		// TODO Auto-generated method stub
		List<CategoryDto> result=null;
		try {
			result = dal.list(company);
			for(int i=0;i<result.size();i++){
				if(result.get(i).getLogo() == null) {
					result.get(i).setLogo(POSConstants.DEFAULT_CAT_LOGO_PATH);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
		
		
	}

}
