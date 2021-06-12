package com.css.pos.service.common;

import java.util.List;

import javax.xml.bind.JAXBException;

import com.css.pos.dto.common.LookupDto;
import com.css.pos.dto.common.LookupTypeDto;

public interface LookupDataService extends CommonService<LookupDto, String>{
	/**
	 * Type
	 */
			
	public boolean addListElement(LookupTypeDto element)  throws JAXBException ;
	public boolean UpdateListElement(LookupTypeDto element)  throws JAXBException ;
	public boolean deleteListElement(LookupTypeDto element)  throws JAXBException ;
	public List<LookupTypeDto> listAlllookupElement()  throws JAXBException ;
	
	/**
	 * lookup element
	 * @param type
	 * @param companyId
	 * @return
	 */
	public List<LookupDto> listAllLookupElements(Integer type, String companyId) ;
	
}
