package com.css.pos.dal.security;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.pos.common.util.Decoder;
import com.css.pos.domain.Branch;
import com.css.pos.domain.Closedcash;
import com.css.pos.domain.Company;
import com.css.pos.domain.Customer;
import com.css.pos.domain.People;
import com.css.pos.domain.Role;
import com.css.pos.dto.security.UserDetailsDto;
@Repository
public class UserDalImpl implements UserDal{
	
	@Autowired
	private SessionFactory sessionfactory;
	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}
	@Transactional
	@Override
	public UserDetailsDto getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public UserDetailsDto authenticate(String userName, String password, String companyId,String branchId) {
		UserDetailsDto tuser = null;
		try {
			List<People> entities = getSessionfactory().getCurrentSession().createCriteria(People.class)
					.add(Restrictions.eq("loginName", userName))
					.add(Restrictions.eq("apppassword", Decoder.encode(password)))
				    .add(Restrictions.eq("companyBean.id",companyId)
				    ).list();
			if (entities != null && !entities.isEmpty()) {
				tuser = new UserDetailsDto();
				People entity = entities.get(0);
				tuser.setId(entity.getId());
				tuser.setFullName(entity.getName());
				tuser.setEmail(entity.getEmail());
				tuser.setPhone(entity.getPhone());
				tuser.setMobile(entity.getMobile());
				tuser.setCard(entity.getCard());
				tuser.setUserName(entity.getLoginName());
				tuser.setImage(entity.getImage());
				tuser.setActiveUntil(entity.getActiveUntil());
				tuser.setCompanyAdmin(entity.getIsCompanyAdmin() == 1);
				tuser.setSuperAdmin(entity.getIsSuperAdmin() == 1);
				tuser.setVisible(entity.getVisible() == 1);
				tuser.setPassword(Decoder.decode(entity.getApppassword()));
				tuser.setAddress(entity.getAddress());
				/**
				 * Load the cash record
				 */
				List<Closedcash> cashes = getSessionfactory().getCurrentSession().createCriteria(Closedcash.class)
						.add(Restrictions.isNull("dateend"))
						.add(Restrictions.eq("casherBean.id", tuser.getId())).list();
				if(cashes!= null && !cashes.isEmpty()) {
					tuser.setMoneyRecordID(cashes.get(0).getMoney());
				}else {
					//create new closed cash and insert it in the DB
					Closedcash money = new Closedcash();
					money.setMoney(UUID.randomUUID().toString());
					money.setDatestart(new Timestamp(new Date().getTime()));
					money.setCasherBean(new People(tuser.getId()));
					money.setBranchBean(new Branch(branchId));
					Branch b = (Branch) getSessionfactory().getCurrentSession().load(Branch.class, branchId);
					money.setHost(b.getName());
					getSessionfactory().getCurrentSession().save(money);
				}
			}
			return tuser;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public List<UserDetailsDto> list(String id) {
		List<UserDetailsDto> users = new ArrayList<>();
		try {
			List<People> entities = getSessionfactory().getCurrentSession().createCriteria(People.class).add(Restrictions.eq("roleBean.id", id)).list();
			if(entities != null && !entities.isEmpty()) {
				UserDetailsDto tuser = null;
				for(People entity:entities) {
					tuser = new  UserDetailsDto();
					//copy from the entity
					tuser.setId(entity.getId());
					tuser.setFullName(entity.getName());
					tuser.setEmail(entity.getEmail());
					tuser.setPhone(entity.getPhone());
					tuser.setMobile(entity.getMobile());
					tuser.setCard(entity.getCard());
					tuser.setUserName(entity.getLoginName());
					tuser.setImage(entity.getImage());
					tuser.setActiveUntil(entity.getActiveUntil());
					tuser.setCompanyAdmin(entity.getIsCompanyAdmin()==1);
					tuser.setSuperAdmin(entity.getIsSuperAdmin()==1);
					tuser.setVisible(entity.getVisible()==1);
					tuser.setPassword(Decoder.decode(entity.getApppassword()));
					tuser.setAddress(entity.getAddress());
					
					users.add(tuser);
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return users;
	}

	@Override
	@Transactional
	public int save(UserDetailsDto viewuser) {
		try {
			People entity= new People();
			entity.setId(viewuser.getId());
			
			Role role = new Role();
			role.setId(viewuser.getRoleId());
			entity.setRoleBean(role);
			entity.setCompanyBean(new Company(viewuser.getCompanyId()));
			entity.setName(viewuser.getFullName());
			entity.setLoginName(viewuser.getUserName().toUpperCase());
			entity.setApppassword(Decoder.encode(viewuser.getPassword()));
			entity.setEmail(viewuser.getEmail());
			entity.setPhone(viewuser.getPhone());
			entity.setMobile(viewuser.getMobile());
			entity.setCard(viewuser.getCard());
			entity.setVisible(viewuser.isVisible()?1:0);
			entity.setAddress(viewuser.getAddress());
			entity.setIsCompanyAdmin(viewuser.isCompanyAdmin()?1:0);
			entity.setActiveUntil(viewuser.getActiveUntil());
			entity.setIsSuperAdmin(viewuser.isSuperAdmin()?1:0);
			sessionfactory.getCurrentSession().saveOrUpdate(entity);
			return 1;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	@Transactional
	public int delete(String id) {
		// TODO Auto-generated method stub
		try {
			sessionfactory.getCurrentSession().delete(new People(id));
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		} 
		
	}

	@Override
	@Transactional
	public boolean isUserMailExists(String email, String dbEmail) {
		try {
			Criteria criteria = sessionfactory.getCurrentSession().createCriteria(People.class)
					.add(Restrictions.eq("email", email)).add(Restrictions.ne("email", dbEmail));
			List<Customer> entities = criteria.list();
			if(entities != null && !entities.isEmpty())
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}// TODO Auto-generated method stub
	}

	@Override
	@Transactional
	public boolean changePassword(String userId, String password) {
		try {
			sessionfactory.getCurrentSession().createSQLQuery("UPDATE people SET apppassword = '"+Decoder.encode(password)+"' WHERE id ='"+userId+"'")
					.executeUpdate();
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
