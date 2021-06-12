package com.css.pos.dal.common;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.pos.domain.Branch;
import com.css.pos.domain.Company;
import com.css.pos.domain.LookupData;
import com.css.pos.dto.common.LookupDto;
import com.css.pos.dto.company.BranchDto;
@Repository
public class LookupDataDalImpl implements LookupDataDal {
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
	public int save(LookupDto element) {
		try {
			LookupData entity = new LookupData();
			entity.setId(element.getId());
			entity.setValue(element.getValue());
			entity.setType(element.getType());
			Company co = new Company();
			co.setId(element.getCompanyId());
			entity.setCompanyBean(co);
			
			sessionfactory.getCurrentSession().saveOrUpdate(entity);
			return 1;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	@Transactional
	public int delete(String id) {
		try {
			LookupData entity = new LookupData();
			entity.setId(id);
			sessionfactory.getCurrentSession().delete(entity);
			return 1;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	@Transactional
	public List<LookupDto> listAllLookupElements(Integer type, String companyId) {
		List<LookupDto> lookups = null;
		try {
			// TODO Auto-generated method stub
			Criteria criteria = sessionfactory.getCurrentSession().createCriteria(LookupData.class).
					add(Restrictions.eq("type", type)).add(Restrictions.eq("companyBean.id", companyId));
			List<LookupData> entities = criteria.list();
			if(entities != null && !entities.isEmpty())
				for (LookupData c : entities) {
					if(lookups == null) lookups = new ArrayList<>();
					lookups.add(new LookupDto(c.getId(),c.getValue(),companyId,type));
				} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lookups;
	}

	@Override
	public List<LookupDto> list(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
