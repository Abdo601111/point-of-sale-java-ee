package com.css.pos.dal.company;

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
import com.css.pos.dto.company.BranchDto;
@Repository
public class BranchDalImpl implements BranchDal {
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public int save(BranchDto branch) {
		try {
			Branch entity = new Branch();
			entity.setId(branch.getId());
			entity.setName(branch.getName());
			entity.setAddress(branch.getAddress());
			entity.setPhone(branch.getPhone());
			entity.setMobile(branch.getMobile());
			Company co = new Company();
			co.setId(branch.getCompanyId());
			entity.setCompany(co);
			sessionFactory.getCurrentSession().saveOrUpdate(entity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	@Transactional
	public int delete(String id) {
		// TODO Auto-generated method stub
		try {
			Branch entity = new Branch();
			entity.setId(id);
			sessionFactory.getCurrentSession().delete(entity);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public BranchDto find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BranchDto> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<BranchDto> list(String company) {
		List<BranchDto> branches = null;
		try {
			// TODO Auto-generated method stub
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Branch.class)
			.add(Restrictions.eq("company.id", company));
			List<Branch> entities = criteria.list();
			if(entities != null && !entities.isEmpty())
				for (Branch c : entities) {
					if(branches == null) branches = new ArrayList<>();
					branches.add(new BranchDto(c.getId(), c.getAddress(), c.getMobile(), c.getName(), c.getPhone(), company));
				} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return branches;
	}
	

}
