package com.css.pos.service.company;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dal.company.BusinessLineDal;
import com.css.pos.dto.company.BusinessLineDto;
@Component
public class BusinessLineServiceImpl implements BusinessLineService {
	@Autowired
	private BusinessLineDal dal;
	public BusinessLineDal getDal() {
		return dal;
	}

	public void setDal(BusinessLineDal dal) {
		this.dal = dal;
	}

	public int save(BusinessLineDto businessLine) {
		try {
			if(businessLine.getId() == null)
				businessLine.setId(UUID.randomUUID().toString());
			return dal.save(businessLine);
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}

	public int delete(String id) {
		// TODO Auto-generated method stub
		return dal.delete(id);
	}

	public BusinessLineDto find(String id) {
		// TODO Auto-generated method stub
		return dal.find(id);
	}


	@Override
	public List<BusinessLineDto> list(String id) {
		// TODO Auto-generated method stub
		return dal.list(id);
	}

}
