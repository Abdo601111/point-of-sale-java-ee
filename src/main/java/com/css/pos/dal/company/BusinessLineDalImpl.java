package com.css.pos.dal.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.pos.domain.BusinessLine;
import com.css.pos.dto.company.BusinessLineDto;

@Repository
public class BusinessLineDalImpl implements BusinessLineDal {
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
	public int save(BusinessLineDto businessLine) {
		try {

			BusinessLine entity = new BusinessLine();
			entity.setId(businessLine.getId());
			entity.setName(businessLine.getName());
			entity.setDescription(businessLine.getDescription());
			entity.setNegativestock(businessLine.getNegativestock() ? new BigDecimal(1) : new BigDecimal(0));
			sessionFactory.getCurrentSession().saveOrUpdate(entity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}
	
	@Transactional
	@Override
	public int delete(String id) {
		try {
			BusinessLine entity = new BusinessLine();
			entity.setId(id);
			sessionFactory.getCurrentSession().delete(entity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public BusinessLineDto find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<BusinessLineDto> list(String id) {
		try {
			List<BusinessLineDto> bls = new ArrayList<>();
			List<BusinessLine> entities = sessionFactory.getCurrentSession().createCriteria(BusinessLine.class).list();
			if (entities != null && !entities.isEmpty()) {
				for (BusinessLine b : entities) {
					bls.add(new BusinessLineDto(b.getId(), b.getDescription(), b.getName(),
							b.getNegativestock().equals(new BigDecimal(1)), false));
				}
			}
			return bls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
