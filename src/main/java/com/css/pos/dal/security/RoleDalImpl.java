package com.css.pos.dal.security;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.css.pos.domain.Company;
import com.css.pos.domain.Role;
import com.css.pos.dto.security.RoleDto;

import javassist.bytecode.stackmap.BasicBlock.Catch;
@Repository
public class RoleDalImpl implements RoleDal {
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
	public int save(RoleDto role) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(new Role(role.getId(),role.getName(),new Company(role.getCompanyId())));
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	@Transactional
	public int delete(String roleId) {
		try {
			sessionFactory.getCurrentSession().delete(new Role(roleId));
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;			
		}

	}

	@Override
	@Transactional
	public List<RoleDto> list(String companyId) {
		List<RoleDto> roles = new ArrayList<>();
		try {
			List<Role> entities = sessionFactory.getCurrentSession().createCriteria(Role.class).add(Restrictions.eq("companyBean.id", companyId)).list();
			if(entities != null && !entities.isEmpty()) {
				for(Role r:entities)
					roles.add(new RoleDto(r.getId(), r.getName(), companyId));
			}
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
