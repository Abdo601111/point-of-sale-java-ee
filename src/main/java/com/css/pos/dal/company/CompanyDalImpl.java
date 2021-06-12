package com.css.pos.dal.company;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.pos.domain.BusinessLine;
import com.css.pos.domain.Company;
import com.css.pos.dto.company.CompanyDto;
@Repository
public class CompanyDalImpl implements CompanyDal {
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	@Override
	public int save(CompanyDto company) {
		try {
			Company entity = new Company();
			entity.setId(company.getId());
			entity.setName(company.getName());
			entity.setAddress(company.getAddress());
			entity.setPhone(company.getPhone());
			entity.setMobile(company.getMobile());
			entity.setLogo(company.getLogo());
			BusinessLine bl = new BusinessLine();
			bl.setId(company.getBusinessLine());
			entity.setBusinessLine(bl);
			sessionFactory.getCurrentSession().saveOrUpdate(entity);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Transactional
	@Override
	public int delete(String id) {
		try {
			Company entity = new Company();
			entity.setId(id);
			sessionFactory.getCurrentSession().delete(entity);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public CompanyDto findCompany(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyDto> listCompanies() {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public List<CompanyDto> list(String bLine) {
		List<CompanyDto> companies = null;
		try {
			// TODO Auto-generated method stub
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Company.class)
			.add(Restrictions.eq("businessLine.id", bLine));
			List<Company> entities = criteria.list();
			if(entities != null && !entities.isEmpty())
				for (Company c : entities) {
					if(companies == null) companies = new ArrayList<>();
					companies.add(new CompanyDto(c.getId(), c.getAddress(), c.getLogo(), c.getMobile(), c.getName(), c.getPhone()));
				} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return companies;
	}

}
