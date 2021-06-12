package com.css.pos.dal.category;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.pos.domain.BusinessLine;
import com.css.pos.domain.Category;
import com.css.pos.domain.Company;
import com.css.pos.dto.category.CategoryDto;
import com.css.pos.dto.company.CompanyDto;
@Repository
public class CategoryDalImpl implements CategoryDal {
	@Autowired
	private SessionFactory sessionfactory;
	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}
	@Override
	@Transactional
	public int save(CategoryDto cat) {
		try {
			Category entity = new Category();
			entity.setId(cat.getId());
			entity.setName(cat.getName());
			entity.setImage(cat.getLogo());
			entity.setHasProducts(cat.isHasProducts()?1:0);
			entity.setParentid(cat.getParentId());
			
			Company co = new Company();
			co.setId(cat.getCompanyId());
			entity.setCompany(co);
			sessionfactory.getCurrentSession().saveOrUpdate(entity);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	@Transactional
	public int delete(String id) {
		try {
			Category entity = new Category();
			entity.setId(id);
			sessionfactory.getCurrentSession().delete(entity);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public CategoryDto find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDto> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<CategoryDto> list(String company) {
		List<CategoryDto> cats = null;
		try {
			// TODO Auto-generated method stub
			Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Category.class)
			.add(Restrictions.eq("company.id", company));
			List<Category> entities = criteria.list();
			if(entities != null && !entities.isEmpty())
				for (Category c : entities) {
					if(cats == null) cats = new ArrayList<>();
					cats.add(new CategoryDto(c.getId(), c.getHasProducts() == 1, c.getImage(),c.getName(), c.getParentid()));
				} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cats;
	}
}
