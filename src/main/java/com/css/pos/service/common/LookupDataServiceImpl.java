package com.css.pos.service.common;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dal.common.LookupDataDal;
import com.css.pos.dto.common.LookupDto;
import com.css.pos.dto.common.LookupTypeDto;
import com.css.pos.dto.common.XMLList;
@Component
public class LookupDataServiceImpl implements LookupDataService {
	@Autowired
	private LookupDataDal lookupDal;
	
	public LookupDataDal getLookupDal() {
		return lookupDal;
	}
	public void setLookupDal(LookupDataDal lookupDal) {
		this.lookupDal = lookupDal;
	}
	private String path;
	private JAXBContext jaxbCtxt;
	public LookupDataServiceImpl() {
		path =  this.getClass().getClassLoader().getResource("").getPath()+"/com/css/pos/dal/common/ListTypes.xml";
		try {
			jaxbCtxt = JAXBContext.newInstance(XMLList.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean addListElement(LookupTypeDto element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean UpdateListElement(LookupTypeDto element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteListElement(LookupTypeDto element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<LookupTypeDto> listAlllookupElement() throws JAXBException {
		Unmarshaller unmarshaller = jaxbCtxt.createUnmarshaller();
        XMLList lsts = (XMLList) unmarshaller.unmarshal(new File(path));
        
		return lsts.getLists();
	}
	@Override
	public int save(LookupDto element) {
		if(element.getId() == null)
			element.setId(UUID.randomUUID().toString());
		return lookupDal.save(element);
	}
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return lookupDal.delete(id);
	}
	@Override
	public List<LookupDto> listAllLookupElements(Integer type, String companyId) {
		// TODO Auto-generated method stub
		return lookupDal.listAllLookupElements(type, companyId);
	}
	@Override
	public List<LookupDto> list(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
